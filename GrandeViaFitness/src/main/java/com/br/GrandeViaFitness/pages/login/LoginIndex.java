package com.br.GrandeViaFitness.pages.login;

import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class LoginIndex extends BasePage
{
   private static final long serialVersionUID = -140731152008355046L;

   public LoginIndex()
   {
      addOrReplace(new LoginForm("formulario", feedback));
   }
}
