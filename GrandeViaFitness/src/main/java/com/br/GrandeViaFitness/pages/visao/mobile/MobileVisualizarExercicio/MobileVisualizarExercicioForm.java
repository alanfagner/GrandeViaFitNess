package com.br.GrandeViaFitness.pages.visao.mobile.MobileVisualizarExercicio;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.exercicio.visualizar.VisualizarExercicioIndex;

public class MobileVisualizarExercicioForm extends FormularioBase<TipoExercicio>
{

   private static final long serialVersionUID = 336643108380788892L;
   private final TipoExercicio tipoExercicio;
   public MobileVisualizarExercicioForm(final String id, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      this.tipoExercicio = tipoExercicio;
      inicializar();
   }

   private void inicializar()
   {
      addOrReplace(new AjaxLink<TipoExercicio>("btnVoltar")
      {
         private static final long serialVersionUID = 2774454734879746242L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new VisualizarExercicioIndex(tipoExercicio));
         }
      });

      criaLabel();
   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnNomeExercicio", tipoExercicio.getNomeExercicio()));
   }

}
