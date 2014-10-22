package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

@AuthorizeInstantiation("R_ADM")
public class MobileMembroCorpoIndex extends BasePageMobile
{
   private static final long serialVersionUID = -8543385107314620902L;

   public MobileMembroCorpoIndex()
   {
      addOrReplace(new MobileMembroCorpoForm(BasePage.FORMULARIO));
   }
}
