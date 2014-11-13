package com.br.GrandeViaFitness.pages.visao.exercicio.consultar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar.CadastrarAlterarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.visualizar.VisualizarExercicioIndex;

public class ConsultarExercicioForm extends FormularioBase<TipoExercicio>
{
   private static final long serialVersionUID = 2773647163866008579L;
   private FeedBackPanelCustom feedBack;
   private WebMarkupContainer informacaoVazia;
   private ConfirmAjaxButtonCustom<TipoExercicio> modal;

   @SpringBean
   private TipoExercicioAS tipoExercicioAS;
   private DataGridGenerica<TipoExercicio, String> gridGenerica;
   private ProviderGenerico<TipoExercicio, String> providerGenerico;
   private TextField<String> campoNome;
   private TextField<String> campoCodigo;
   private DropDownChoice<TipoEquipamento> campoEquipamento;
   private DropDownChoice<Corpo> campoMembro;

   public ConsultarExercicioForm(final String id)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(new TipoExercicio()));
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();
      criaModal();
      criaGridCliente();
      criaFeedBack();
      criaCampos();

   }

   private void criaCampos()
   {
      campoCodigo = new TextField<String>("codigo");
      campoNome = new TextField<String>("nomeExercicio");
      addOrReplace(campoCodigo, campoNome);

      campoEquipamento =
         new DropDownChoice<TipoEquipamento>("tipoEquipamento", tipoExercicioAS.buscaListaEquipamento(),
            new ChoiceRenderer<TipoEquipamento>("nomeTipoEquip", "codigo"))
         {

            private static final long serialVersionUID = 7581567009731600947L;

            @Override
            public void renderHead(final IHeaderResponse response)
            {

               final String script = " $( '#" + getMarkupId() + "' ).selectmenu().selectmenu('menuWidget').addClass('overflow');";
               response.render(OnDomReadyHeaderItem.forScript(script));
            }
         };

      campoMembro =
         new DropDownChoice<Corpo>("corpo", tipoExercicioAS.buscaListaCorpo(), new ChoiceRenderer<Corpo>("nomeMembroCorpo",
            "codigo"))
         {

            private static final long serialVersionUID = 7581567009731600947L;

            @Override
            public void renderHead(final IHeaderResponse response)
            {

               final String script = " $( '#" + getMarkupId() + "' ).selectmenu().selectmenu('menuWidget').addClass('overflow');";
               response.render(OnDomReadyHeaderItem.forScript(script));
            }
         };
      addOrReplace(campoMembro, campoEquipamento);

   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedBack");
      feedBack.setOutputMarkupId(true);
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia, feedBack);
   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<TipoExercicio>("modal", "Excluir", "Excluir", "Confirma a exclusão do exercício selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if(tipoExercicioAS.verificaHistorio(getEntidade())){
            tipoExercicioAS.excluirExercicio(getEntidade());
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Exercício"));
            atualizaTela(target, gridGenerica, feedBack);
            }else{
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M019, "Atividade"));
               target.add(feedBack);
            }

         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            // TODO Auto-generated method stub

         }
      };
      addOrReplace(modal);
   }

   private void criaGridCliente()
   {
      final List<IColumn<TipoExercicio, String>> columns = new ArrayList<IColumn<TipoExercicio, String>>();
      final List<AjaxLink<TipoExercicio>> listaBotoes = new ArrayList<AjaxLink<TipoExercicio>>();
      listaBotoes.add(new AjaxLink<TipoExercicio>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });
      listaBotoes.add(new AjaxLink<TipoExercicio>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new VisualizarExercicioIndex(getModelObject()));
         }
      });
      columns.add(DataGridGenerica.criaColunarTipoExercico("Código", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarTipoExercico("Nome", "nomeExercicio", true, 20));
      columns.add(DataGridGenerica.criaColunarTipoExercico("Equipamento", "tipoEquipamento.nomeTipoEquip", true, 20));
      columns.add(DataGridGenerica.criaColunarTipoExercico("Membro", "corpo.nomeMembroCorpo", true, 20));
      columns.add(new AbstractColumn<TipoExercicio, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<TipoExercicio>> cellItem, final String componentId,
            final IModel<TipoExercicio> entidade)
         {
            cellItem.add(new ActionButtonPanel<TipoExercicio>(componentId, entidade, listaBotoes, true, true));

         }
      });
      gridGenerica = new DataGridGenerica<TipoExercicio, String>("table", columns, getProviderGenerico(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
            informacaoVazia.setVisibilityAllowed(getItemCount() == 0);
         }
      };
      gridGenerica.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenerica);
   }

   private void criaBotoes()
   {
      final AjaxButtonCustom btnNovoCliente = new AjaxButtonCustom("btnNovoExercicio")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarExercicioIndex());
         }

      };

      final AjaxButtonCustom btnPesquisar = new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            gridGenerica.size();
            target.add(gridGenerica, informacaoVazia);
         }

      };

      final AjaxButtonCustom btnVoltar = new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = 7630777092486610559L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new HomePageIndex());
         }
      };
      addOrReplace(btnNovoCliente, btnVoltar, btnPesquisar);

   }

   public ProviderGenerico<TipoExercicio, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<TipoExercicio, String>(tipoExercicioAS, getModelObject());
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }

}
