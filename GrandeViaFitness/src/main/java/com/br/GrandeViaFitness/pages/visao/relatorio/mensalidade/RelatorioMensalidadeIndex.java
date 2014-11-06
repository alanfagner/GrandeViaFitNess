package com.br.GrandeViaFitness.pages.visao.relatorio.mensalidade;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class RelatorioMensalidadeIndex extends BasePage
{
   private static final long serialVersionUID = 5538047127459089444L;

   public RelatorioMensalidadeIndex()
   {
      addOrReplace(new RelatorioMensalidadeForm(BasePage.FORMULARIO));
   }

}
