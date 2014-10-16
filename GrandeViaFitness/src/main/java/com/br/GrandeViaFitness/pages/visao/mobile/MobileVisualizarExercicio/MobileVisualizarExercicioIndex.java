package com.br.GrandeViaFitness.pages.visao.mobile.MobileVisualizarExercicio;

import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

public class MobileVisualizarExercicioIndex extends BasePageMobile
{
   private static final long serialVersionUID = 4571970844421198398L;

   public MobileVisualizarExercicioIndex(final TipoExercicio tipoExercicio)
   {
      addOrReplace(new MobileVisualizarExercicioForm(BasePage.FORMULARIO, tipoExercicio));
   }
}
