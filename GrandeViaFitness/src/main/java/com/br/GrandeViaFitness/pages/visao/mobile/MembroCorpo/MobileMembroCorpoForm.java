package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio.MobileTipoExercicoIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class MobileMembroCorpoForm extends FormularioBase<Corpo>
{
   private static final long serialVersionUID = -2384361859690420515L;
   @SpringBean
   private CorpoAS corpoAS;
   private RepeatingView listaCorpoView;
   private final Date dataCadastro;

   public MobileMembroCorpoForm(final String id, final Date dataCadastro)
   {
      super(id);
      this.dataCadastro = dataCadastro;
      setOutputMarkupId(true);
      inicializar();

   }

   private void inicializar()
   {
      criaLabel();
      criaListaView();
      criaBotoes();
   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnLogado", getUsuarioLogado().getNomePessoa()));
      addOrReplace(new Label("lbnDataSelecionada", Util.formataData(dataCadastro, "dd/MM/yyyy")));
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxLink<Corpo>("btnVoltar")
      {
         private static final long serialVersionUID = -267927957082911090L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileHomeIndex());
         }
      });

   }

   private void criaListaView()
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
               setResponsePage(new MobileTipoExercicoIndex(getModelObject(), dataCadastro));

            }
         };
         link.add(new Label("lbnNome", auxCorpo.getNomeMembroCorpo()));
         list.add(link);
         listaCorpoView.add(list);
      }
      addOrReplace(listaCorpoView);
   }
}
