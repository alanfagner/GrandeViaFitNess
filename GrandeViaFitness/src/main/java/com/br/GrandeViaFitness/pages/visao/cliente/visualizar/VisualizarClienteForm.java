package com.br.GrandeViaFitness.pages.visao.cliente.visualizar;

import java.text.SimpleDateFormat;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.consultar.ConsultarClienteIndex;

public class VisualizarClienteForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 7501425089750436151L;
   Pessoa pessoa;

   public VisualizarClienteForm(final String id, final Pessoa pessoa)
   {
      super(id, new CompoundPropertyModel<Pessoa>(pessoa));
      this.pessoa = pessoa;
      inicializar();
   }

   private void inicializar()
   {
      criaLabel();
      criaBotoes();

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("alterar")
      {
         private static final long serialVersionUID = -8644157391966938694L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarClienteIndex(pessoa));
         }
      });

      addOrReplace(new AjaxButtonCustom("voltar")
      {
         private static final long serialVersionUID = -2853532978867429273L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new ConsultarClienteIndex());
         }
      });

   }

   private void criaLabel()
   {
      addOrReplace(new Label("nomePessoa"));
      addOrReplace(new Label("cpfPessoa"));
      addOrReplace(new Label("sexo.descricao"));
      addOrReplace(new Label("emailPessoa"));
      addOrReplace(new Label("dataNascimentoPessoa", new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDataNascimentoPessoa())));
      addOrReplace(new Label("numeroCelulaPessoa"));
      addOrReplace(new Label("endereco.cep"));
      addOrReplace(new Label("numeroResidencial"));
      addOrReplace(new Label("endereco.logradouro"));
      addOrReplace(new Label("endereco.bairro"));
      addOrReplace(new Label("endereco.cidade"));
      addOrReplace(new Label("endereco.estado"));
   }

}
