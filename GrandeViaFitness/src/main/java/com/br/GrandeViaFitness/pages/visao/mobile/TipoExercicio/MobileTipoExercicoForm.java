package com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileVisualizarExercicio.MobileVisualizarExercicioIndex;

public class MobileTipoExercicoForm extends FormularioBase<TipoExercicio>
{
   private static final long serialVersionUID = 5504236696353530227L;

   @SpringBean
   private TipoExercicioAS tipoExercicioAS;
   private final Corpo corpo;

   private ListView<TipoExercicio> listViewTipoExercicio;

   private RepeatingView listaCorpoView;
   public MobileTipoExercicoForm(final String id, final Corpo corpo)
   {
      super(id);
      this.corpo = corpo;
      inicializar();
      setMobile(true);
   }

   private void inicializar()
   {
      listaCorpoView = new RepeatingView("listItems");
      for (final TipoExercicio auxTipoExercici : tipoExercicioAS.buscaListaPorCorpo(corpo))
      {
         final WebMarkupContainer list = new WebMarkupContainer(listaCorpoView.newChildId());
         final AjaxLink<TipoExercicio> link = new AjaxLink<TipoExercicio>("Link")
         {
            private static final long serialVersionUID = -267927957082911090L;

            @Override
            public void onClick(final AjaxRequestTarget target)
            {
               setResponsePage(new MobileVisualizarExercicioIndex(auxTipoExercici));

            }
         };
         link.add(new Label("lbnNome", auxTipoExercici.getNomeExercicio()));
         list.add(link);
         listaCorpoView.add(list);
      }
      addOrReplace(listaCorpoView);

      addOrReplace(new AjaxLink<TipoExercicio>("btnVoltar")
      {
         private static final long serialVersionUID = 1782016523465683542L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileMembroCorpoIndex());

         }
      });


   }

}
