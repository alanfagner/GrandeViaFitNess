package com.br.GrandeViaFitness.pages.visao.venda.fecharVenda;

import java.util.List;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda.EfetuarVendaIndex;

@AuthorizeInstantiation("R_ADM")
public class FechaVendaIndex extends BasePage
{
   private static final long serialVersionUID = 3225397556529411250L;

   public FechaVendaIndex()
   {
      setResponsePage(new EfetuarVendaIndex());
   }

   public FechaVendaIndex(final List<RlProdutoVenda> listaVenda)
   {
      addOrReplace(new FechaVendaForm(BasePage.FORMULARIO, listaVenda));
   }

}
