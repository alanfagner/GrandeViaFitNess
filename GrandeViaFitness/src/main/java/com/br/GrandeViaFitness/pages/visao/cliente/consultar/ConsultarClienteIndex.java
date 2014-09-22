package com.br.GrandeViaFitness.pages.visao.cliente.consultar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class ConsultarClienteIndex extends BasePage
{
   private static final long serialVersionUID = -2712295311634178525L;

   public ConsultarClienteIndex()
   {
      add(new ConsultarClienteForm("formulario"));
   }
}
