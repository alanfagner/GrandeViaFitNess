package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio.MobileTipoExercicoIndex;

public class MobileMembroCorpoForm extends FormularioBase<Corpo>
{
   private static final long serialVersionUID = -2384361859690420515L;
   @SpringBean
   private CorpoAS corpoAS;
   private RepeatingView listaCorpoView;

   public MobileMembroCorpoForm(final String id)
   {
      super(id);
      setOutputMarkupId(true);
      setMobile(true);
      inicializar();
   }

   private void inicializar()
   {

      listaCorpoView = new RepeatingView("listItems");

      for (final Corpo auxCorpo : corpoAS.recuperaListaCorpo())
      {
         final WebMarkupContainer list = new WebMarkupContainer(listaCorpoView.newChildId());
         final AjaxLink<Corpo> link = new AjaxLink<Corpo>("Link")
         {
            private static final long serialVersionUID = -267927957082911090L;

            @Override
            public void onClick(final AjaxRequestTarget target)
            {
               setResponsePage(new MobileTipoExercicoIndex(getModelObject()));

            }
         };
         link.add(new Label("lbnNome", auxCorpo.getNomeMembroCorpo()));
         list.add(link);
         listaCorpoView.add(list);
      }
      addOrReplace(listaCorpoView);
   }
}
