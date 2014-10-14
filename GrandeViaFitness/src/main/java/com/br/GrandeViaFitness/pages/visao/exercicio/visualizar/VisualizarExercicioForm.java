package com.br.GrandeViaFitness.pages.visao.exercicio.visualizar;

import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.TipoExercicio;

public class VisualizarExercicioForm extends FormularioBase<TipoExercicio>
{

   private static final long serialVersionUID = -8224166606149623890L;

   public VisualizarExercicioForm(final String id, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
   }

}
