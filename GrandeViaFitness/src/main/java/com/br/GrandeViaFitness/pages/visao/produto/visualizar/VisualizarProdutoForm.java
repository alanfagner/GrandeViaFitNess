package com.br.GrandeViaFitness.pages.visao.produto.visualizar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.pages.visao.produto.cadastrar.CadastrarAlterarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.produto.consultar.ConsultarProdutoIndex;

public class VisualizarProdutoForm extends FormularioBase<Produto>
{
   private static final long serialVersionUID = 7163122338598317605L;

   public VisualizarProdutoForm(final String id, final Produto produto)
   {
      super(id, new CompoundPropertyModel<Produto>(produto));
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
         private static final long serialVersionUID = -3901891057903238592L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarProdutoIndex(VisualizarProdutoForm.this.getModelObject()));
         }
      });

      addOrReplace(new AjaxButtonCustom("voltar")
      {
         private static final long serialVersionUID = 4048733426592128163L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new ConsultarProdutoIndex());
         }
      });

   }

   private void criaLabel()
   {

      addOrReplace(new Label("nomeProduto"));
      addOrReplace(new Label("valorProduto"));

   }

}
