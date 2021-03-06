package com.br.GrandeViaFitness.pages.visao.produto.consultar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class ConsultarProdutoIndex extends BasePage
{
   private static final long serialVersionUID = -7063448593174510229L;

   public ConsultarProdutoIndex()
   {
      addOrReplace(new ConsultarProdutoForm(BasePage.FORMULARIO));
   }
}
