package com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.ProdutoAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.ConfirmAjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenericoMemoria;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.venda.fecharVenda.FechaVendaIndex;

public class EfetuarVendaForm extends FormularioBase<Venda>
{
   private static final long serialVersionUID = -2379657526732075658L;

   @SpringBean
   private ProdutoAS produtoAS;

   private WebMarkupContainer informacaoVaziaProduto;
   private WebMarkupContainer informacaoVaziaRlProdutoVenda;
   private ProviderGenerico<Produto, String> providerGenericoProduto;
   private ProviderGenericoMemoria<RlProdutoVenda, String> providerVenda;
   private Produto filtroProduto;
   private final List<RlProdutoVenda> listaVenda;
   private FeedBackPanelCustom feedBack;
   private ConfirmAjaxButtonCustom<RlProdutoVenda> modal;
   private DataGridGenerica<Produto, String> gridGenericaProduto;
   private DataGridGenerica<RlProdutoVenda, String> gridGenericaRlProdutoVenda;
   private TextField<String> campoQuantidade;

   private TextField<String> campoNomeProduto;

   public EfetuarVendaForm(final String id, final List<RlProdutoVenda> lista)
   {
      super(id);
      listaVenda = lista;
      inicializar();
   }

   private void inicializar()
   {
      filtroProduto = new Produto();
      criaInformacao();
      criaGridProduto();
      criaGridVenda();
      criaCampos();
      criaFeedBack();
      criaModal();
      criaBotoes();
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = -2486992900861386768L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new HomePageIndex());
         }

      });

      addOrReplace(new AjaxButtonCustom("btnFecharVenda")
      {
         private static final long serialVersionUID = -2645311189906696705L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (listaVenda.size() < 1)
            {
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M011));
               target.add(feedBack);
               return;
            }
            setResponsePage(new FechaVendaIndex(listaVenda));
         }
      });

      addOrReplace(new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = -2645311189906696705L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            gridGenericaProduto.size();
            target.add(gridGenericaProduto, informacaoVaziaProduto);
         }
      });

      addOrReplace(new AjaxButtonCustom("btnLimpar")
      {
         private static final long serialVersionUID = -2645311189906696705L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new EfetuarVendaIndex(EfetuarVendaForm.this.listaVenda));
         }
      });

   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<RlProdutoVenda>("modal", "Excluir", "Excluir", "Deseja excluir esse produto da venda ?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            listaVenda.remove(modal.getEntidade());
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Produto"));
            atualizaTela(target, gridGenericaRlProdutoVenda, feedBack, informacaoVaziaRlProdutoVenda);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {

         }
      };
      addOrReplace(modal);
   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);

   }

   private void criaCampos()
   {
      campoQuantidade = new TextField<String>("quantidade", new Model<String>());
      campoQuantidade.setOutputMarkupId(true);
      campoNomeProduto = new TextField<String>("nomeProduto", new PropertyModel<String>(filtroProduto, "nomeProduto"));
      addOrReplace(campoQuantidade, campoNomeProduto);
   }

   private void criaInformacao()
   {
      informacaoVaziaProduto = new WebMarkupContainer("containerVazioProduto");
      informacaoVaziaProduto.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVaziaProduto);

      informacaoVaziaRlProdutoVenda = new WebMarkupContainer("containerVazioRLProdutoVenda");
      informacaoVaziaRlProdutoVenda.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVaziaRlProdutoVenda);

   }

   private void criaGridProduto()
   {
      final List<IColumn<Produto, String>> columns = new ArrayList<IColumn<Produto, String>>();
      final List<AjaxLink<Produto>> listaBotoes = new ArrayList<AjaxLink<Produto>>();
      listaBotoes.add(new AjaxLink<Produto>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {

         }
      });
      listaBotoes.add(new AjaxLink<Produto>("Adicionar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            if (campoQuantidade.getModelObject() != null && !campoQuantidade.getModelObject().equals(""))
            {
               final RlProdutoVenda auxProdudoVenda = new RlProdutoVenda();
               auxProdudoVenda.setQuantidadeVendido(Integer.parseInt(campoQuantidade.getModelObject()));
               auxProdudoVenda.setProduto(getModelObject());
               Boolean isExiste = true;
               for (final RlProdutoVenda auxListProdutoVenda : listaVenda)
               {
                  if (auxListProdutoVenda.getProduto().getCodigo().compareTo(auxProdudoVenda.getProduto().getCodigo()) == 0)
                  {
                     auxListProdutoVenda.setQuantidadeVendido(auxListProdutoVenda.getQuantidadeVendido()
                        + auxProdudoVenda.getQuantidadeVendido());
                     isExiste = false;
                  }
               }
               if (isExiste)
               {
                  listaVenda.add(auxProdudoVenda);
               }
            }
            else
            {
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Quantidade"));
            }
            target.add(gridGenericaRlProdutoVenda, feedBack, informacaoVaziaRlProdutoVenda, campoQuantidade);
         }
      });
      columns.add(DataGridGenerica.criaColunarProduto("Código", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarProduto("Nome Produto", "nomeProduto", true, 40));
      columns.add(DataGridGenerica.criaColunarProduto("Valor Produto", "valorMascara", true, 10));
      columns.add(new AbstractColumn<Produto, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<Produto>> cellItem, final String componentId, final IModel<Produto> entidade)
         {
            cellItem.add(new ActionButtonPanel<Produto>(componentId, entidade, listaBotoes, false, true));

         }
      });
      gridGenericaProduto = new DataGridGenerica<Produto, String>("tableProduto", columns, getProviderGenericoProduto(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
            informacaoVaziaProduto.setVisibilityAllowed(getItemCount() == 0);
         }
      };
      gridGenericaProduto.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenericaProduto);

   }

   private void criaGridVenda()
   {
      final List<IColumn<RlProdutoVenda, String>> columns = new ArrayList<IColumn<RlProdutoVenda, String>>();
      final List<AjaxLink<RlProdutoVenda>> listaBotoes = new ArrayList<AjaxLink<RlProdutoVenda>>();
      listaBotoes.add(new AjaxLink<RlProdutoVenda>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }

      });
      listaBotoes.add(new AjaxLink<RlProdutoVenda>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {

         }
      });
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Nome Produto", "produto.nomeProduto", true, 30));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Quantidade", "quantidadeVendido", true, 10));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Valor Unitario", "produto.valorMascara", true, 10));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Valor Total Produto", "valorTotal", true, 20));
      columns.add(new AbstractColumn<RlProdutoVenda, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<RlProdutoVenda>> cellItem, final String componentId,
            final IModel<RlProdutoVenda> entidade)
         {
            cellItem.add(new ActionButtonPanel<RlProdutoVenda>(componentId, entidade, listaBotoes, true, false));

         }
      });
      gridGenericaRlProdutoVenda = new DataGridGenerica<RlProdutoVenda, String>("tableProdutoVenda", columns, getProviderVenda(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
            informacaoVaziaRlProdutoVenda.setVisibilityAllowed(getItemCount() == 0);
         }
      };
      gridGenericaRlProdutoVenda.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenericaRlProdutoVenda);
   }

   public ProviderGenericoMemoria<RlProdutoVenda, String> getProviderVenda()
   {
      if (providerVenda == null)
      {
         providerVenda = new ProviderGenericoMemoria<RlProdutoVenda, String>(listaVenda);
      }
      return providerVenda;
   }

   public ProviderGenerico<Produto, String> getProviderGenericoProduto()
   {
      if (providerGenericoProduto == null)
      {
         providerGenericoProduto = new ProviderGenerico<Produto, String>(produtoAS, filtroProduto);
         providerGenericoProduto.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenericoProduto;
   }
}
