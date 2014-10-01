package com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.TipoExercicio;

public class CadastrarAlterarExercicioForm extends FormularioBase<TipoExercicio>
{
   private static final long serialVersionUID = 8617765087518222595L;
   private final Boolean isAlterar;
   private final TipoExercicio tipoExercicio;
   public CadastrarAlterarExercicioForm(final String id, final Boolean isAlterar, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      this.tipoExercicio = tipoExercicio;
      this.isAlterar = isAlterar;
      inicializar();
   }

   private void inicializar()
   {
      crialabel();

   }

   private void crialabel()
   {
      if (isAlterar)
      {
         addOrReplace(new Label("Label1", "Alterar Exercício"));
      }
      else
      {
         addOrReplace(new Label("Label1", "Cadastrar Novo Exercício"));
      }

   }

}
