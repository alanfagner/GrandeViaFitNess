package com.br.GrandeViaFitness.pages.visao.mensalidade.pagamento;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class PagamentoIndex extends BasePage
{
   private static final long serialVersionUID = 8609504567500847972L;

   public PagamentoIndex()
   {
      addOrReplace(new PagamentoForm(BasePage.FORMULARIO));
   }
}
