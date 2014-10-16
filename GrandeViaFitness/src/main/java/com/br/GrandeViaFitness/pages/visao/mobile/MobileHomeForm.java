package com.br.GrandeViaFitness.pages.visao.mobile;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Pessoa;
import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;

public class MobileHomeForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = -3231330308077618602L;

   @SpringBean
   private CorpoAS corpoAS;

   public MobileHomeForm(final String id)
   {
      super(id);
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
               private static final long serialVersionUID = 3857456599376607826L;
            };

            btnCorpo.add(new AttributeModifier("value", item.getModelObject().getNomeMembroCorpo()));
            item.add(btnCorpo);
         }

      };
      addOrReplace(listMembro);

   }

}
