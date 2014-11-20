package com.br.GrandeViaFitness.pages.visao.relatorio.mensalidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.MensalidadeAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.enumUtil.MesReferenciaEnum;
import com.br.GrandeViaFitness.model.Mensalidade;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.mensalidade.pagamento.PagamentoIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class RelatorioMensalidadeForm extends FormularioBase<Mensalidade>
{
   private static final long serialVersionUID = 2731041304214686423L;
   private DataGridGenerica<Mensalidade, String> gridGenerica;
   private WebMarkupContainer informacaoVazia;
   private ProviderGenerico<Mensalidade, String> providerGenerico;
   @SpringBean
   private MensalidadeAS mensalidadeAS;
   private TextField<Date> campoDataInicio;
   private TextField<Date> campoDataFim;
   private FeedBackPanelCustom feedBack;
   private TextField<Pessoa> campoNome;
   private DropDownChoice<MesReferenciaEnum> comboMesReferencia;
   private TextField<Integer> campoAnoReferencia;
   private ConfirmAjaxButtonCustom<Mensalidade> modal;
   private Label labelValor;

   public RelatorioMensalidadeForm(final String id)
   {
      super(id, new CompoundPropertyModel<Mensalidade>(new Mensalidade()));
      getModelObject().setPessoa(new Pessoa());
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();
      criaGrid();
      criaInformacao();
      criaCampos();
      criaFeedBack();
      criaComboBox();
      criaModal();
      criaLabel();
   }

   private void criaLabel()
   {
      labelValor = new Label("valorAtual", mensalidadeAS.calculaSaldo(getModelObject()));
      labelValor.setOutputMarkupId(true);
      addOrReplace(labelValor);

   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<Mensalidade>("modal", "Excluir", "Excluir", "Confirma a exclusão da mensalidade selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            mensalidadeAS.excluirMensalidade(getEntidade());
            criaLabel();;
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Mensalidade"));
            atualizaTela(target, gridGenerica, feedBack, labelValor);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {

         }
      };
      addOrReplace(modal);
   }

   private void criaComboBox()
   {
      comboMesReferencia =
         new DropDownChoice<MesReferenciaEnum>("mesReferente", new PropertyModel<MesReferenciaEnum>(getModelObject(), "mesReferente"),
            Arrays.asList(MesReferenciaEnum.values()), new ChoiceRenderer<MesReferenciaEnum>("descricao", "codigo"))
         {
            private static final long serialVersionUID = 8205686180697927445L;

            @Override
            public void renderHead(final IHeaderResponse response)
            {

               final String script = " $( '#" + getMarkupId() + "' ).selectmenu().selectmenu('menuWidget').addClass('overflow');";
               response.render(OnDomReadyHeaderItem.forScript(script));
            }
         };
      addOrReplace(comboMesReferencia);

   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);

   }

   private void criaCampos()
   {
      campoDataInicio = new TextField<Date>("dataInicio", new Model<Date>())
      {
         private static final long serialVersionUID = 2548803204602177351L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').datepicker();";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      };

      campoDataFim = new TextField<Date>("dataFim", new Model<Date>())
      {
         private static final long serialVersionUID = 2548803204602177351L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').datepicker();";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      };
      campoAnoReferencia = new TextField<Integer>("anoReferencia");
      campoNome = new TextField<Pessoa>("nomePessoa", new PropertyModel<Pessoa>(getModelObject().getPessoa(), "nomePessoa"));
      addOrReplace(campoDataInicio, campoDataFim, campoNome, campoAnoReferencia);
   }

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia);
   }

   private void criaGrid()
   {
      final List<IColumn<Mensalidade, String>> columns = new ArrayList<IColumn<Mensalidade, String>>();
      final List<AjaxLink<Mensalidade>> listaBotoes = new ArrayList<AjaxLink<Mensalidade>>();

      listaBotoes.add(new AjaxLink<Mensalidade>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }
      });
      columns.add(DataGridGenerica.criaColunarMensalidade("Nome", "pessoa.nomePessoa", true, 30));
      columns.add(DataGridGenerica.criaColunarMensalidade("Data Pagamento", "dataPagamentoFormatada", true, 10));
      columns.add(DataGridGenerica.criaColunarMensalidade("Valor", "valorPagoFormatado", true, 20));
      columns.add(DataGridGenerica.criaColunarMensalidade("Referente ao Mês", "mesReferente.descricao", true, 10));
      columns.add(DataGridGenerica.criaColunarMensalidade("Referente ao Ano ", "anoReferencia", true, 10));
      columns.add(new AbstractColumn<Mensalidade, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<Mensalidade>> cellItem, final String componentId,
            final IModel<Mensalidade> entidade)
         {
            cellItem.add(new ActionButtonPanel<Mensalidade>(componentId, entidade, listaBotoes, true, true));

         }
      });
      gridGenerica = new DataGridGenerica<Mensalidade, String>("table", columns, getProviderGenerico(), 5)
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

   public ProviderGenerico<Mensalidade, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Mensalidade, String>(mensalidadeAS, getModelObject());
         providerGenerico.setOrdernar(new ParametrosOrdenacao("dataPagamento", false));
      }
      return providerGenerico;
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("btnNovaMensalidade")
      {
         private static final long serialVersionUID = -9060421957899887701L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new PagamentoIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = -9060421957899887701L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new HomePageIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = -9060421957899887701L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (carregaData())
            {
               gridGenerica.size();
               criaLabel();
               target.add(gridGenerica, informacaoVazia, labelValor);
            }
            target.add(feedBack);
         }

      });

      addOrReplace(new AjaxButtonCustom("BtnLimpar")
      {
         private static final long serialVersionUID = -9060421957899887701L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new RelatorioMensalidadeIndex());
         }
      });
   }

   private Boolean carregaData()
   {
      Boolean valido = true;

      if (campoDataFim.getModelObject() != null)
      {
         try
         {
            RelatorioMensalidadeForm.this.getModelObject().setDataFim(Util.converteData(campoDataFim.getModelObject()));
         }
         catch (final Exception e)
         {
            getSession().error(Mensagem.recuperaMensagem(Mensagem.M016, "FIM"));
            valido = false;
         }

         if (campoDataInicio.getModelObject() != null)
         {
            try
            {
               RelatorioMensalidadeForm.this.getModelObject().setDataPagamento(Util.converteData(campoDataInicio.getModelObject()));
               if (RelatorioMensalidadeForm.this.getModelObject().getDataPagamento()
                  .after(RelatorioMensalidadeForm.this.getModelObject().getDataFim()))
               {
                  getSession().error(Mensagem.recuperaMensagem(Mensagem.M017, "Data de Inicio", "Data Fim"));
                  valido = false;
               }
            }
            catch (final Exception e)
            {
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M016, "Inicio"));
               valido = false;
            }


         }
         else
         {
            getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Data de Inicio"));
            valido = false;
         }
      }
      else
      {
         if (campoDataInicio.getModelObject() != null)
         {
            try
            {
               RelatorioMensalidadeForm.this.getModelObject().setDataPagamento(Util.converteData(campoDataInicio.getModelObject()));
            }
            catch (final Exception e)
            {
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M016, "Inicio"));
               valido = false;
            }
         }
      }

      return valido;
   }

}
