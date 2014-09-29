package com.br.GrandeViaFitness.pages.visao.aparelhos;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class ConsultarAparelhosIndex extends BasePage
{
   private static final long serialVersionUID = -2280554240634769505L;

   public ConsultarAparelhosIndex()
   {
      addOrReplace(new ConsultarAparelhosForm("formulario"));
   }
}
