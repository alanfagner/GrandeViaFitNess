package com.br.GrandeViaFitness.pages.visao.produto.visualizar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.produto.consultar.ConsultarProdutoIndex;

@AuthorizeInstantiation("R_ADM")
public class VisualizarProdutoIndex extends BasePage
{
   private static final long serialVersionUID = -5196590731468384553L;

   public VisualizarProdutoIndex()
   {
      setResponsePage(new ConsultarProdutoIndex());
   }

   public VisualizarProdutoIndex(final Produto produto)
   {
      addOrReplace(new VisualizarProdutoForm(BasePage.FORMULARIO, produto));
   }

}
