package com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

@AuthorizeInstantiation("R_ADM")
public class MobileExecutarExercicioIndex extends BasePageMobile
{
   public MobileExecutarExercicioIndex(final TipoExercicio tipoExercicio)
   {
      final RlPessoaExercicio auxPessoaExercicio = new RlPessoaExercicio();
      auxPessoaExercicio.setTipoExercicio(tipoExercicio);
      add(new MobileExecutarExercicioFrom(BasePage.FORMULARIO, auxPessoaExercicio));
   }

   private static final long serialVersionUID = 2588451429792268189L;

}
