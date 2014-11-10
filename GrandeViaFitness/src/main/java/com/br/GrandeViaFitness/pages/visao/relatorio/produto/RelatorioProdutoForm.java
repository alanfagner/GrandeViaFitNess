package com.br.GrandeViaFitness.pages.visao.relatorio.produto;

import java.util.ArrayList;
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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.VendaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda.EfetuarVendaIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class RelatorioProdutoForm extends FormularioBase<Venda>
{
   private static final long serialVersionUID = 8146738387706296039L;
   private DataGridGenerica<Venda, String> gridGenerica;
   private WebMarkupContainer informacaoVazia;
   private ConfirmAjaxButtonCustom<Venda> modal;
   private ProviderGenerico<Venda, String> providerGenerico;
   @SpringBean
   private VendaAS vendaAS;
   private FeedBackPanelCustom feedBack;
   private TextField<Date> campoDataInicio;
   private TextField<Date> campoDataFim;
   private TextField<String> campoPessoa;
   private Label labelValor;

   public RelatorioProdutoForm(final String id)
   {
      super(id, new CompoundPropertyModel<Venda>(new Venda()));
      getModelObject().setPessoa(new Pessoa());
      inicializar();
      criaInformacao();
   }

   private void inicializar()
   {
      criaFeedBack();
      criaGrid();
      criaModal();
      criaBotes();
      criaCampos();
      criaLabel();
   }

   private void criaLabel()
   {
      labelValor = new Label("valorAtual", vendaAS.calculaSaldo(getModelObject()));
      labelValor.setOutputMarkupId(true);
      addOrReplace(labelValor);

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

      campoPessoa = new TextField<String>("nome", new PropertyModel<String>(getModelObject().getPessoa(), "nomePessoa"));
      addOrReplace(campoDataFim, campoDataInicio, campoPessoa);

   }

   private void criaBotes()
   {
      addOrReplace(new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = 3711746860250830488L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (carregaData())
            {
               criaLabel();
               target.add(gridGenerica, informacaoVazia, labelValor);
            }
            target.add(feedBack);
         }
      });

      addOrReplace(new AjaxButtonCustom("BtnLimpar")
      {
         private static final long serialVersionUID = 3711746860250830488L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new RelatorioProdutoIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnNovaVenda")
      {
         private static final long serialVersionUID = 3711746860250830488L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new EfetuarVendaIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = 3711746860250830488L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new HomePageIndex());
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
            RelatorioProdutoForm.this.getModelObject().setDataFim(Util.converteData(campoDataFim.getModelObject()));
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
               RelatorioProdutoForm.this.getModelObject().setDataVenda(Util.converteData(campoDataInicio.getModelObject()));
               if (RelatorioProdutoForm.this.getModelObject().getDataVenda().after(RelatorioProdutoForm.this.getModelObject().getDataFim()))
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
               RelatorioProdutoForm.this.getModelObject().setDataVenda(Util.converteData(campoDataInicio.getModelObject()));
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
   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);
   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<Venda>("modal", "Excluir", "Excluir", "Confirma a exclusão da venda selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            vendaAS.excluir(modal.getEntidade());
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Venda"));
            atualizaTela(target, gridGenerica, feedBack);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {

         }
      };
      addOrReplace(modal);
   }

   private void criaGrid()
   {
      final List<IColumn<Venda, String>> columns = new ArrayList<IColumn<Venda, String>>();
      final List<AjaxLink<Venda>> listaBotoes = new ArrayList<AjaxLink<Venda>>();

      listaBotoes.add(new AjaxLink<Venda>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }
      });

      listaBotoes.add(new AjaxLink<Venda>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {

         }
      });
      columns.add(DataGridGenerica.criaColunarVenda("Nome", "pessoa.nomePessoa", true, 30));
      columns.add(DataGridGenerica.criaColunarVenda("Data Pagamento", "dataFormatada", true, 10));
      columns.add(DataGridGenerica.criaColunarVenda("Valor Total", "valorFormatado", true, 10));
      columns.add(new AbstractColumn<Venda, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<Venda>> cellItem, final String componentId, final IModel<Venda> entidade)
         {
            cellItem.add(new ActionButtonPanel<Venda>(componentId, entidade, listaBotoes, true, true));

         }
      });
      gridGenerica = new DataGridGenerica<Venda, String>("table", columns, getProviderGenerico(), 5)
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

   public ProviderGenerico<Venda, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Venda, String>(vendaAS, getModelObject());
         providerGenerico.setOrdernar(new ParametrosOrdenacao("dataVenda", false));
      }
      return providerGenerico;
   }

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia);
   }

}
