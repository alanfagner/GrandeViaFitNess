package com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.RlPessoaExercicioAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileVisualizarExercicio.MobileVisualizarExercicioIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class MobileExecutarExercicioFrom extends FormularioBase<RlPessoaExercicio>
{

   private static final long serialVersionUID = 303054331978593054L;

   @SpringBean
   private RlPessoaExercicioAS rlPessoaExercicioAS;
   private final RlPessoaExercicio rlPessoaExercicio;

   private FeedbackPanel feedBack;
   private PageableListView<RlPessoaExercicio> listaHistorio;

   public MobileExecutarExercicioFrom(final String id, final RlPessoaExercicio pessoaExercico)
   {
      super(id, new CompoundPropertyModel<RlPessoaExercicio>(pessoaExercico));
      rlPessoaExercicio = pessoaExercico;
      inicializar();
   }

   private void inicializar()
   {

      criaBotoes();
      criaHistorico();
      criaCampos();
      criaFeedBack();
   }

   private void criaFeedBack()
   {
      feedBack = new FeedbackPanel("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);

      addOrReplace(feedBack);
   }

   private void criaCampos()
   {
      addOrReplace(new TextField<String>("qtdPeso", new PropertyModel<String>(rlPessoaExercicio, "quatidadePeso"))
      {
         private static final long serialVersionUID = 93660142455076401L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').mask('9?99,9', {reverse : true});";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      });
      addOrReplace(new TextField<String>("qtdRepeticao", new PropertyModel<String>(rlPessoaExercicio, "numeroRepeticoes"))
      {
         private static final long serialVersionUID = -1775362462445067248L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').mask('9?99999');";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      });
      addOrReplace(new TextField<String>("numeroSeries", new PropertyModel<String>(rlPessoaExercicio, "numeroSeries"))
      {
         private static final long serialVersionUID = 5135924870616258474L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').mask('9?99999');";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      });

   }

   private void criaHistorico()
   {
      final WebMarkupContainer containerListView = new WebMarkupContainer("container");
      listaHistorio =
         new PageableListView<RlPessoaExercicio>("listaHistorico", rlPessoaExercicioAS.buscaListaExercicio(rlPessoaExercicio), 5)
         {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<RlPessoaExercicio> item)
            {

               item.add(new Label("lbnData", Util.getDataPorExtenso(item.getModelObject().getDataExercicio())));
               item.add(new Label("lbnRepeticoes", item.getModelObject().getNumeroRepeticoes().toString()));
               item.add(new Label("lbnSeries", item.getModelObject().getNumeroRepeticoes().toString()));
               item.add(new Label("lbnPeso", item.getModelObject().getQuatidadePeso().toString()));

            }

            @Override
            public void renderHead(final IHeaderResponse response)
            {
               final String script = "$('#listview').listview().listview('refresh');";
               response.render(OnDomReadyHeaderItem.forScript(script));
            }

         };

      containerListView.setOutputMarkupId(true);
      containerListView.addOrReplace(listaHistorio);
      containerListView.add(new AjaxPagingNavigator("navigator", listaHistorio)
      {
         private static final long serialVersionUID = -8967592560248268743L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {

         }
      });

      addOrReplace(containerListView);
   }

   private boolean validaCampos()
   {
      Boolean valida = true;
      if (rlPessoaExercicio.getNumeroRepeticoes() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "número de repetições"));
         valida = false;
      }

      if (rlPessoaExercicio.getQuatidadePeso() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "quantidade de peso"));
         valida = false;
      }

      if (rlPessoaExercicio.getNumeroSeries() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "número de series"));
         valida = false;
      }

      return valida;
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButton("salvar")
      {
         private static final long serialVersionUID = -8031422717986871238L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampos())
            {
               rlPessoaExercicio.setDataExercicio(new Date());
               rlPessoaExercicioAS.persisteDados(rlPessoaExercicio);
               criaHistorico();
            }
            atualizaTela(target, feedBack);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            atualizaTela(target, feedBack);
         }

      });

      addOrReplace(new AjaxButton("btnVoltar")
      {
         private static final long serialVersionUID = 7044710643044229617L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new MobileVisualizarExercicioIndex(rlPessoaExercicio.getTipoExercicio()));
         }
      });

   }
}
