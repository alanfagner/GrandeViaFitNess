package com.br.GrandeViaFitness.pages.visao.venda.detalharVenda;

import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.relatorio.produto.RelatorioProdutoIndex;

public class DetalharVendaIndex extends BasePage
{
   private static final long serialVersionUID = -2000303689167602877L;

   public DetalharVendaIndex()
   {
      setResponsePage(new RelatorioProdutoIndex());
   }

   public DetalharVendaIndex(final Venda venda)
   {
      addOrReplace(new DetalharVendaForm(BasePage.FORMULARIO, venda));
   }
}
