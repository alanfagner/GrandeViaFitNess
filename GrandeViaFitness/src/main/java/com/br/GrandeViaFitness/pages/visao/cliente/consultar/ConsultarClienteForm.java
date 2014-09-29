package com.br.GrandeViaFitness.pages.visao.cliente.consultar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.AS.PessoaAS;
import com.br.GrandeViaFitness.Componentes.BotoesGrid;
import com.br.GrandeViaFitness.Componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.Componentes.FormularioBase;
import com.br.GrandeViaFitness.Componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.Componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.Componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.Enum.Mensagem;
import com.br.GrandeViaFitness.Model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.visualizar.VisualizarClienteIndex;
import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;
import com.googlecode.wicket.jquery.ui.form.button.ConfirmAjaxButton;

public class ConsultarClienteForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 553958619270523962L;

   private DataGridGenerica<Pessoa, String> gridGenerica;
   private final Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   @SpringBean
   private PessoaAS pessoaAS;
   private final FeedbackPanel feedBack;

   private TextField<Long> campoCodigo;

   private TextField<String> campoNome;

   private TextField<String> campoCpf;

   private TextField<String> campoEmail;

   private ConfirmAjaxButton confirma;

   private AjaxButton botaoPesquisar;
   private WebMarkupContainer informacaoVazia;

   private ConfirmAjaxButtonCustom<Pessoa> modal;

   public ConsultarClienteForm(final String id, final FeedbackPanel feedBack)
   {
      super(id, new CompoundPropertyModel<Pessoa>(new Pessoa()));
      this.feedBack = feedBack;
      filtro = getModelObject();
      inicializar();
   }

   private void inicializar()
   {
      criaInformacao();
      criaBotoes();
      criaCampos();
      criaGridCliente();
      criaModal();
   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<Pessoa>("modal", "Excluir", "Excluir", "Confirma a exclusão do cliente selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            pessoaAS.excluirPessoa(getEntidade());
            atualizaTela(target);
            success(Mensagem.M02.getDescricao());
            target.add(gridGenerica, feedBack);

         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            // TODO Auto-generated method stub

         }
      };
      addOrReplace(modal);
   }

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia);

   }

   private void criaGridCliente()
   {
      final List<IColumn<Pessoa, String>> columns = new ArrayList<IColumn<Pessoa, String>>();
      final BotoesGrid<Pessoa, String> listBotoes = new BotoesGrid<Pessoa, String>();
      listBotoes.add(new AjaxLink<Pessoa>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });
      listBotoes.add(new AjaxLink<Pessoa>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new VisualizarClienteIndex(getModelObject()));
         }
      });
      columns.add(DataGridGenerica.criaColunar("Codigo", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunar("Nome", "nomePessoa", true, 40));
      columns.add(DataGridGenerica.criaColunar("CPF", "cpfPessoa", true, 10));
      columns.add(DataGridGenerica.criaColunar("Email", "emailPessoa", true, 40));

      columns.add(listBotoes.criaListaBotoes());
      gridGenerica = new DataGridGenerica<Pessoa, String>("table", columns, getProviderGenerico(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
            informacaoVazia.setVisibilityAllowed(getItemCount() == 0);
         }
      };
      gridGenerica.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenerica);
   }

   private void criaCampos()
   {
      campoCodigo = new TextField<Long>("codigo");
      campoNome = new TextField<String>("nomePessoa");
      campoCpf = new TextField<String>("cpfPessoa");
      campoEmail = new TextField<String>("emailPessoa");

      campoCodigo.setLabel(new Model<String>("Codigo"));
      campoNome.setLabel(new Model<String>("Nome"));
      campoCpf.setLabel(new Model<String>("Cpf"));
      campoEmail.setLabel(new Model<String>("Email"));

      addOrReplace(campoCodigo, campoNome, campoCpf, campoEmail);

   }

   private void criaBotoes()
   {
      botaoPesquisar = new AjaxButton("btnPesquisar")
      {
         private static final long serialVersionUID = -2955360194609797122L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            atualizaTela(target);
            target.add(gridGenerica, informacaoVazia);
         }
      };

      final Button btnNovoCliente = new Button("btnNovoCliente")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new CadastrarAlterarClienteIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };

      final Button btnVoltar = new Button("btnVoltar")
      {
         private static final long serialVersionUID = 7630777092486610559L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new HomePageIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };
      addOrReplace(btnNovoCliente, btnVoltar, botaoPesquisar);
   }

   public ProviderGenerico<Pessoa, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Pessoa, String>(pessoaAS, filtro);
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }
}
