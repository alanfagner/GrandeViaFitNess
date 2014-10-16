package com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
   public MobileTipoExercicoForm(final String id, final Corpo corpo)
   {
      super(id);
      this.corpo = corpo;
      inicializar();
   }

   private void inicializar()
   {
      listViewTipoExercicio = new ListView<TipoExercicio>("listaExercicios", tipoExercicioAS.buscaListaPorCorpo(corpo))
      {
         private static final long serialVersionUID = -3445329734799104136L;

         @Override
         protected void populateItem(final ListItem<TipoExercicio> item)
         {
            final AjaxButton btnAjaxTipoExercicio = new AjaxButton("btnTipoExercicio")
            {
               private static final long serialVersionUID = 6373340180300553820L;

               @Override
               protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
               {
                  setResponsePage(new MobileVisualizarExercicioIndex(item.getModelObject()));
               }
            };
            btnAjaxTipoExercicio.add(new AttributeModifier("value", item.getModelObject().getNomeExercicio()));
            item.add(btnAjaxTipoExercicio);
         }

      };
      listViewTipoExercicio.setOutputMarkupId(true);

      addOrReplace(new AjaxLink<TipoExercicio>("btnVoltar")
      {
         private static final long serialVersionUID = 1782016523465683542L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileMembroCorpoIndex());

         }
      });

      addOrReplace(listViewTipoExercicio);

   }

}
