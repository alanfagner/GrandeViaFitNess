package com.br.GrandeViaFitness.pages.visao.relatorio.produto;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class RelatorioProdutoIndex extends BasePage
{
   private static final long serialVersionUID = -5620375732713323751L;

   public RelatorioProdutoIndex()
   {
      addOrReplace(new RelatorioProdutoForm(BasePage.FORMULARIO));
   }
}
