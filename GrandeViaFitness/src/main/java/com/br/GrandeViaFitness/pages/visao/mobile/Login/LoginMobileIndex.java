package com.br.GrandeViaFitness.pages.visao.mobile.Login;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

public class LoginMobileIndex extends BasePageMobile
{
   private static final long serialVersionUID = -5260284480322506632L;

   public LoginMobileIndex()
   {
      addOrReplace(new LoginMobileForm(BasePage.FORMULARIO));
   }

}
