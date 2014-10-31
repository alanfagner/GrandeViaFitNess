package com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class EfetuarVendaIndex extends BasePage
{
   private static final long serialVersionUID = 96587519975113815L;

   public EfetuarVendaIndex()
   {
      addOrReplace(new EfetuarVendaForm(BasePage.FORMULARIO));
   }

}
