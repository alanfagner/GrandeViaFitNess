package com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Pessoa;
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
   private final Pessoa usuarioAtividade;

   public MobileMembroCorpoForm(final String id, final Date dataCadastro, final Pessoa usuarioAtividade)
   {
      super(id);
      this.usuarioAtividade = usuarioAtividade;
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
      addOrReplace(new Label("lbnLogado", usuarioAtividade.getNomePessoa()));
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

      List<Corpo> auxAlista = new ArrayList<Corpo>();
      auxAlista = corpoAS.recuperaListaCorpo();
      Collections.sort(auxAlista, new Comparator<Corpo>()
      {

         @Override
         public int compare(final Corpo o1, final Corpo o2)
         {
            return o1.getNomeMembroCorpo().compareTo(o2.getNomeMembroCorpo());
         }
      });

      for (final Corpo auxCorpo : auxAlista)
      {
         final WebMarkupContainer list = new WebMarkupContainer(listaCorpoView.newChildId());

         final AjaxLink<Corpo> link = new AjaxLink<Corpo>("Link")
         {
            private static final long serialVersionUID = -267927957082911090L;

            @Override
            public void onClick(final AjaxRequestTarget target)
            {
               setResponsePage(new MobileTipoExercicoIndex(auxCorpo, dataCadastro, usuarioAtividade));

            }
         };
         link.add(new Label("lbnNome", auxCorpo.getNomeMembroCorpo()));
         list.add(link);
         listaCorpoView.add(list);
      }
      addOrReplace(listaCorpoView);
   }
}
