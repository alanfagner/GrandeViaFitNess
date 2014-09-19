package com.br.GrandeViaFitness.pages.visao.cliente.cadastrar;


import java.util.Arrays;
import java.util.Calendar;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import com.br.GrandeViaFitness.Enum.Mensagem;
import com.br.GrandeViaFitness.Enum.PermissaoEnum;
import com.br.GrandeViaFitness.Enum.SexoEnum;
import com.br.GrandeViaFitness.Utilitario.Util;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.cliente.consultar.ConsultarClienteIndex;
import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;

public class CadastrarAlterarClienteFrom extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 4650600559626073083L;

   private final Pessoa pessoa;

   private TextField<String> campoNome;
   private TextField<String> campoCpf;
   private DropDownChoice<SexoEnum> campoSexo;
   private TextField<String> campoEmail;

   private DatePicker campoDataNascimento;

   private DropDownChoice<PermissaoEnum> campoPermissao;

   private TextField<String> campoTelefone;

   private TextField<String> campoCEP;

   private TextField<String> campoNumero;

   private TextField<String> campoLogradouro;

   private TextField<String> campoBairro;

   private TextField<String> campoCidade;

   private TextField<String> campoEstado;

   private AjaxButton botaoSalvar;

   private Button botaoVoltar;
   @SpringBean
   private PessoaAS pessoaAS;

   private final FeedbackPanel feedBack;

   public CadastrarAlterarClienteFrom(final String id, final Pessoa pessoa, final FeedbackPanel feedBack)
   {
      super(id, new CompoundPropertyModel<Pessoa>(pessoa));
      this.feedBack = feedBack;
      this.pessoa = pessoa;
      inicializar();
   }

   private void inicializar()
   {
      if (this.pessoa.getCodigo() == null)
      {
         addOrReplace(new Label("Label1", "Cadastrar Cliente"));
      }
      else
      {
         addOrReplace(new Label("Label1", "Alterar Cliente"));
      }
      criaCampos();
      criaBotoes();
   }


   private void criaBotoes()
   {
      botaoSalvar = new AjaxButton("btnSalvar")
      {
         private static final long serialVersionUID = -8143189803017939442L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampos(campoNome, campoCpf, campoSexo, campoEmail, campoDataNascimento, campoTelefone, campoPermissao, campoCEP,
               campoLogradouro, campoNumero, campoBairro, campoCidade, campoEstado))
            {
               pessoa.setDataNascimentoPessoa(campoDataNascimento.getModelObject());
               final Calendar calendar = Calendar.getInstance();
               calendar.setTime(pessoa.getDataNascimentoPessoa());
               pessoa
                  .setSenhaPessoa(new Md5PasswordEncoder().encodePassword(pessoa.getCpfPessoa().substring(0, 3) + calendar.get(1), null));
               pessoa.setAuthority(pessoaAS.getAuthorityServico().findAuthority(campoPermissao.getModelObject().getSigla()));
               pessoaAS.save(pessoa);
               success(Mensagem.M01.getDescricao());
               setResponsePage(new ConsultarClienteIndex());
            }
            target.add(feedBack, campoBairro);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            target.add(feedBack);
         }
      };

      botaoVoltar = new Button("btnVoltar")
      {
         private static final long serialVersionUID = -3511983353019317955L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new ConsultarClienteIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };
      addOrReplace(botaoSalvar, botaoVoltar);
   }

   private boolean validaCampos(final Component... components)
   {
      boolean flag = true;
      for (final Component component : components)
      {
         if (component.getDefaultModelObject() == null && flag)
         {
            error("Favor preencher todos os campos do formulario");
            flag = false;
         }
      }

      return flag;
   }
   private void criaCampos()
   {
      campoNome = new TextField<String>("nomePessoa");
      campoNome.setLabel(new Model<String>("Nome"));
      campoCpf = new TextField<String>("cpfPessoa");
      campoCpf.add(new AjaxFormComponentUpdatingBehavior("onChange")
      {
         private static final long serialVersionUID = -8099289544869034996L;
         @Override
         protected void onUpdate(final AjaxRequestTarget target)
         {
            if (campoCpf.getModelObject() != null
               && pessoaAS.getPessoaServico().buscaPessoaPorCpf(Util.retirarMascara(campoCpf.getModelObject())) != null)
            {
               error("Esse Cpf já cadastrado.");
            }
            atualizaTela(target);
            target.add(feedBack);
         }
      });
      campoSexo =
         new DropDownChoice<SexoEnum>("sexo", Arrays.asList(SexoEnum.values()), new ChoiceRenderer<SexoEnum>("descricao", "codigo"));
      campoEmail = new TextField<String>("emailPessoa");
      campoDataNascimento = new DatePicker("dataNascimentoPessoa");
      campoDataNascimento.setLabel(new Model<String>("Data de nascimento"));
      campoTelefone = new TextField<String>("numeroCelulaPessoa");
      campoPermissao =
         new DropDownChoice<PermissaoEnum>("permissao", Arrays.asList(PermissaoEnum.values()), new ChoiceRenderer<PermissaoEnum>(
            "descricao",
            "sigla"));

      campoPermissao.add(new AjaxEventBehavior("onChange")
      {
         private static final long serialVersionUID = 7864566450050198194L;
         @Override
         protected void onEvent(final AjaxRequestTarget target)
         {

         }
      });

      campoPermissao.setDefaultModel(new Model<PermissaoEnum>());
      campoCEP = new TextField<String>("endereco.cep");
      campoCEP.add(new AjaxFormComponentUpdatingBehavior("onBlur")
      {
         private static final long serialVersionUID = -4144690730728093322L;

         @Override
         protected void onUpdate(final AjaxRequestTarget target)
         {
            if (campoCEP.getModelObject() != null)
            {
               pessoa.setEndereco(pessoaAS.getEnderecoServico().buscaEnderecoPorCEP(Util.retirarMascara(campoCEP.getModelObject())));
               if (pessoa.getEndereco() == null)
               {
                  bloqueaCamposEndereco(true);
               }
            }
            atualizaTela(target);
            target.add(campoLogradouro, campoBairro, campoCidade, campoEstado);

         }

         @Override
         protected void onError(final AjaxRequestTarget target, final RuntimeException e)
         {
            atualizaTela(target);
            target.add(feedBack);
         }
      });
      campoNumero = new TextField<String>("numeroResidencial");
      campoLogradouro = new TextField<String>("endereco.logradouro");
      campoLogradouro.setOutputMarkupId(true);
      campoBairro = new TextField<String>("endereco.bairro");
      campoBairro.setOutputMarkupId(true);
      campoCidade = new TextField<String>("endereco.cidade");
      campoCidade.setOutputMarkupId(true);
      campoEstado = new TextField<String>("endereco.estado");
      campoEstado.setOutputMarkupId(true);
      bloqueaCamposEndereco(false);
      addOrReplace(campoNome, campoCpf, campoCpf, campoSexo, campoEmail, campoDataNascimento, campoTelefone, campoPermissao, campoCEP,
         campoNumero, campoLogradouro, campoBairro, campoCidade, campoEstado);

   }

   public void bloqueaCamposEndereco(final Boolean flag)
   {
      campoLogradouro.setEnabled(flag);
      campoBairro.setEnabled(flag);
      campoCidade.setEnabled(flag);
      campoEstado.setEnabled(flag);
   }
}
