package com.br.GrandeViaFitness.pages.visao.exercicio.consultar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar.CadastrarAlterarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.visualizar.VisualizarExercicioIndex;
import com.br.GrandeViaFitness.vo.AparelhosVO;

public class ConsultarExercicioForm extends FormularioBase<AparelhosVO>
{
   private static final long serialVersionUID = 2773647163866008579L;
   private FeedbackPanel feedBack;
   private WebMarkupContainer informacaoVazia;
   private ConfirmAjaxButtonCustom<TipoExercicio> modal;

   @SpringBean
   private TipoExercicioAS tipoExercicioAS;
   private DataGridGenerica<TipoExercicio, String> gridGenerica;
   private ProviderGenerico<TipoExercicio, String> providerGenerico;
   private TipoExercicio filtro;

   public ConsultarExercicioForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();
      filtro = new TipoExercicio();
      criaModal();
      criaGridCliente();
      criaFeedBack();

   }

   private void criaFeedBack()
   {
      feedBack = new FeedbackPanel("feedBack");
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
            tipoExercicioAS.excluirExercicio(getEntidade());
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Exercício"));
            atualizaTela(target, gridGenerica, feedBack);
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
      columns.add(DataGridGenerica.criaColunarTipoExercico("Codigo", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarTipoExercico("Nome", "nomeExercicio", true, 20));
      columns.add(DataGridGenerica.criaColunarTipoExercico("Descrição", "descricaoExercicio", true, 10));
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
            cellItem.add(new ActionButtonPanel<TipoExercicio>(componentId, entidade, listaBotoes));

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
      final Button btnNovoCliente = new Button("btnNovoExercicio")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new CadastrarAlterarExercicioIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };

      final Button btnVoltar = new Button("btnVoltar")
      {
         private static final long serialVersionUID = 7630777092486610559L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new HomePageIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };
      addOrReplace(btnNovoCliente, btnVoltar);

   }

   public ProviderGenerico<TipoExercicio, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<TipoExercicio, String>(tipoExercicioAS, filtro);
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }

}
