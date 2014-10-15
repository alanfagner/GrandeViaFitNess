package com.br.GrandeViaFitness.pages.visao.mobile;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

public class MobileHomeIndex extends BasePageMobile
{
   private static final long serialVersionUID = -288730058844300988L;

   public MobileHomeIndex()
   {
      addOrReplace(new MobileHomeForm(BasePage.FORMULARIO));
   }

}
