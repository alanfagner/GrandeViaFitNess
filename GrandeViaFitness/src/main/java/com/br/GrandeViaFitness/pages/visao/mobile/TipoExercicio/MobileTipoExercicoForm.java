package com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileDetalharExercicio.MobileDetalharExercicioIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class MobileTipoExercicoForm extends FormularioBase<TipoExercicio>
{
   private static final long serialVersionUID = 5504236696353530227L;

   @SpringBean
   private TipoExercicioAS tipoExercicioAS;
   private final Corpo corpo;

   private final Date dataCadastro;
   private RepeatingView listaCorpoView;
   private final Pessoa usuarioAtividade;

   public MobileTipoExercicoForm(final String id, final Corpo corpo, final Date dataCadastro, final Pessoa usuarioAtividade)
   {
      super(id);
      this.usuarioAtividade = usuarioAtividade;
      this.corpo = corpo;
      this.dataCadastro = dataCadastro;
      inicializar();
   }

   private void inicializar()
   {
      criaListView();
      criaLabel();
      criaBotoes();
   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnLogado", usuarioAtividade.getNomePessoa()));
      addOrReplace(new Label("lbnDataSelecionada", Util.formataData(dataCadastro, "dd/MM/yyyy")));
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxLink<TipoExercicio>("btnVoltar")
      {
         private static final long serialVersionUID = 1782016523465683542L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileMembroCorpoIndex(dataCadastro, usuarioAtividade));
         }
      });
   }

   private void criaListView()
   {
      listaCorpoView = new RepeatingView("listItems");
      for (final TipoExercicio auxTipoExercici : tipoExercicioAS.buscaListaTipoExercicioPorCorpo(corpo))
      {
         final WebMarkupContainer list = new WebMarkupContainer(listaCorpoView.newChildId());
         final AjaxLink<TipoExercicio> link = new AjaxLink<TipoExercicio>("Link")
         {
            private static final long serialVersionUID = -267927957082911090L;

            @Override
            public void onClick(final AjaxRequestTarget target)
            {
               setResponsePage(new MobileDetalharExercicioIndex(auxTipoExercici, dataCadastro, usuarioAtividade));
            }
         };
         link.add(new Label("lbnNome", auxTipoExercici.getNomeExercicio()));
         list.add(link);
         listaCorpoView.add(list);
      }
      addOrReplace(listaCorpoView);
   }
}
