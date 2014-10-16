package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

public class MobileMembroCorpoIndex extends BasePageMobile
{
   private static final long serialVersionUID = -8543385107314620902L;

   public MobileMembroCorpoIndex()
   {
      addOrReplace(new MobileMembroCorpoForm(BasePage.FORMULARIO));
   }
}
