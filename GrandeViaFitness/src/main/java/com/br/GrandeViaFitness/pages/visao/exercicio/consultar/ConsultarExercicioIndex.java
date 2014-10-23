package com.br.GrandeViaFitness.pages.visao.exercicio.consultar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class ConsultarExercicioIndex extends BasePage
{
   private static final long serialVersionUID = -2280554240634769505L;

   public ConsultarExercicioIndex()
   {
      addOrReplace(new ConsultarExercicioForm("formulario"));
   }
}
