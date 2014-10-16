package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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

   public MobileMembroCorpoForm(final String id)
   {
      super(id);
      setOutputMarkupId(true);
      inicializar();
   }

   private void inicializar()
   {

      final ListView<Corpo> listMembro = new ListView<Corpo>("listaCorpo", corpoAS.recuperaListaCorpo())
      {
         private static final long serialVersionUID = 5660002990222805270L;

         @Override
         protected void populateItem(final ListItem<Corpo> item)
         {
            final AjaxButton btnCorpo = new AjaxButton("btnCorpo")
            {
               private static final long serialVersionUID = 4258612567022548456L;

               @Override
               protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
               {
                  setResponsePage(new MobileTipoExercicoIndex(item.getModelObject()));
               }
            };
            btnCorpo.add(new AttributeModifier("value", item.getModelObject().getNomeMembroCorpo()));
            btnCorpo.setOutputMarkupId(true);
            item.add(btnCorpo);
         }
      };
      listMembro.setOutputMarkupId(true);
      this.add(listMembro);

   }
}
