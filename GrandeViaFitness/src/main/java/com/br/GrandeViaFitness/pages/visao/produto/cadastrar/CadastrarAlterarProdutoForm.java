package com.br.GrandeViaFitness.pages.visao.produto.cadastrar;

import java.math.BigDecimal;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.ProdutoAS;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.pages.visao.produto.consultar.ConsultarProdutoIndex;

public class CadastrarAlterarProdutoForm extends FormularioBase<Produto>
{
   private static final long serialVersionUID = -8629530699938940261L;
   private TextField<String> campoNome;
   private TextField<String> campoValor;
   private final Produto produto;
   @SpringBean
   private ProdutoAS produtoAS;

   private FeedBackPanelCustom feedBackPanelCustom;

   public CadastrarAlterarProdutoForm(final String id, final Produto produto)
   {
      super(id, new CompoundPropertyModel<Produto>(produto));
      this.produto = produto;

      inicializar();
   }

   private void inicializar()
   {
      criaCampos();
      criaBotoes();
      criafeedBack();
   }

   private void criafeedBack()
   {
      feedBackPanelCustom = new FeedBackPanelCustom("feedback");
      addOrReplace(feedBackPanelCustom);

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("btnNovoProduto")
      {
         private static final long serialVersionUID = -4629383593202385393L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampos())
            {
               produto.setValorProduto(new BigDecimal(campoValor.getModelObject().replace(",", "")));
               if (produto.getCodigo() == null)
               {
                  getSession().success(Mensagem.recuperaMensagem(Mensagem.M01, "Produto"));
               }
               else
               {
                  getSession().success(Mensagem.recuperaMensagem(Mensagem.M03, "Produto"));
               }
               produtoAS.persisteDados(produto);
               setResponsePage(new ConsultarProdutoIndex());
            }
            target.add(feedBackPanelCustom);
         }
      });
      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = -1768450751297077181L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new ConsultarProdutoIndex());
         }
      });
   }

   private boolean validaCampos()
   {
      Boolean valida = true;

      if (produto.getNomeProduto() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "nome produto"));
         valida = false;
      }

      if (campoValor.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "valor produto"));
         valida = false;
      }

      return valida;
   }

   private void criaCampos()
   {
      campoNome = new TextField<String>("nomeProduto");
      campoValor = new TextField<String>("valorProduto", new Model<String>());
      if (produto.getValorProduto() != null)
      {
         campoValor.setModelObject(produto.getValorProduto().toString());
      }
      addOrReplace(campoNome, campoValor);
   }

}
