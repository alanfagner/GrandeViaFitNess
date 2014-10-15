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
      criaFormulario(new TipoExercicio(), false);
   }

   private void criaFormulario(final TipoExercicio tipoExercicio, final Boolean isAlteracao)
   {
      add(new CadastrarAlterarExercicioForm(BasePage.FORMULARIO, isAlteracao, tipoExercicio));
   }
   public CadastrarAlterarExercicioIndex(final TipoExercicio tipoExercicio)
   {
      criaFormulario(tipoExercicio, true);
   }

}
