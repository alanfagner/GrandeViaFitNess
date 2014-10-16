package com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio;

import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

public class MobileTipoExercicoIndex extends BasePageMobile
{
   private static final long serialVersionUID = 3614663156458644305L;

   public MobileTipoExercicoIndex(final Corpo corpo)
   {
      addOrReplace(new MobileTipoExercicoForm(BasePage.FORMULARIO, corpo));
   }
}
