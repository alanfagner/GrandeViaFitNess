package com.br.GrandeViaFitness.pages.visao.exercicio.visualizar;

import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;


public class VisualizarExercicioIndex extends BasePage
{
   private static final long serialVersionUID = 4215112051320119167L;

   public VisualizarExercicioIndex(final TipoExercicio tipoExercicio){
      addOrReplace(new VisualizarExercicioForm(BasePage.FORMULARIO, tipoExercicio));
   }
}
