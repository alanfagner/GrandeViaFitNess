package com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class CadastrarAlterarExercicioIndex extends BasePage
{
   private static final long serialVersionUID = -6313280889970624538L;

   public CadastrarAlterarExercicioIndex()
   {
      add(new CadastrarAlterarExercicioForm(BasePage.FORMULARIO, false, new TipoExercicio()));
   }

}
