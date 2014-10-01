package com.br.GrandeViaFitness.pages.visao;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class HomePageIndex extends BasePage
{
   private static final long serialVersionUID = 1527503466087026093L;

   public HomePageIndex()
   {
      add(new HomePageForm(BasePage.FORMULARIO));
   }
}
