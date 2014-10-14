package com.br.GrandeViaFitness.pages.visao.exercicio.visualizar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class VisualizarExercicioIndex extends BasePage
{
   private static final long serialVersionUID = 4215112051320119167L;

   public VisualizarExercicioIndex(final TipoExercicio tipoExercicio){
      addOrReplace(new VisualizarExercicioForm(BasePage.FORMULARIO, tipoExercicio));
   }
}
