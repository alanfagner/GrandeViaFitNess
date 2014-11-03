package com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class EfetuarVendaIndex extends BasePage
{
   private static final long serialVersionUID = 96587519975113815L;

   public EfetuarVendaIndex()
   {
      criaForm(new ArrayList<RlProdutoVenda>());
   }

   public EfetuarVendaIndex(final List<RlProdutoVenda> lista)
   {
      criaForm(lista);
   }

   private void criaForm(final List<RlProdutoVenda> lista)
   {
      addOrReplace(new EfetuarVendaForm(BasePage.FORMULARIO, lista));
   }

}
