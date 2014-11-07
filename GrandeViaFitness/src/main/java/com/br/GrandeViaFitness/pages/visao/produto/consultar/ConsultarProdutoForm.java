package com.br.GrandeViaFitness.pages.visao.produto.consultar;

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
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.produto.cadastrar.CadastrarAlterarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.produto.visualizar.VisualizarProdutoIndex;

public class ConsultarProdutoForm extends FormularioBase<Produto>
{
   private static final long serialVersionUID = -6753759304326222158L;
   private FeedBackPanelCustom feedBackPanelCustom;
   private TextField<String> campoCodigo;
   private TextField<String> campoNome;
   private Produto filtro;
   private WebMarkupContainer informacaoVazia;
   private DataGridGenerica<Produto, String> gridGenerica;
   private ProviderGenerico<Produto, String> providerGenerico;
   @SpringBean
   private ProdutoAS produtoAS;
   private ConfirmAjaxButtonCustom<Produto> modal;

   public ConsultarProdutoForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      filtro = new Produto();
      criaBotoes();
      criaCampos();
      criaGrid();
      criaFeedBack();
      criaInformacao();
      criaModal();

   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<Produto>("modal", "Excluir", "Excluir", "Confirma a exclusão do produto selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            produtoAS.excluirPessoa(getEntidade());
            success(Mensagem.recuperaMensagem(Mensagem.M02, "Produto"));
            atualizaTela(target, gridGenerica, feedBackPanelCustom);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            // TODO Auto-generated method stub

         }
      };
      addOrReplace(modal);
   }

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia);

   }

   private void criaGrid()
   {
      final List<IColumn<Produto, String>> columns = new ArrayList<IColumn<Produto, String>>();
      final List<AjaxLink<Produto>> listaBotoes = new ArrayList<AjaxLink<Produto>>();
      listaBotoes.add(new AjaxLink<Produto>("Excluir")
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
      listaBotoes.add(new AjaxLink<Produto>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new VisualizarProdutoIndex(getModelObject()));
         }
      });
      columns.add(DataGridGenerica.criaColunarProduto("Codigo", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarProduto("Nome", "nomeProduto", true, 40));
      columns.add(DataGridGenerica.criaColunarProduto("Preço", "valorMascara", true, 40));
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
            cellItem.add(new ActionButtonPanel<Produto>(componentId, entidade, listaBotoes, true, true));

         }
      });
      gridGenerica = new DataGridGenerica<Produto, String>("table", columns, getProviderGenerico(), 5)
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

   private void criaCampos()
   {
      campoCodigo = new TextField<String>("codigo", new Model<String>());
      campoNome = new TextField<String>("nomeProduto", new Model<String>());
      addOrReplace(campoCodigo, campoNome);

   }

   private void criaFeedBack()
   {
      feedBackPanelCustom = new FeedBackPanelCustom("feedback");
      addOrReplace(feedBackPanelCustom);

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("btnNovo")
      {
         private static final long serialVersionUID = 5599582280742051303L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarProdutoIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = 5599582280742051303L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new HomePageIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = 5599582280742051303L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            filtro.setCodigo(Long.parseLong(campoCodigo.getModelObject() != null ? campoCodigo.getModelObject() : ""));
            filtro.setNomeProduto(campoNome.getModelObject());
            gridGenerica.size();
            target.add(gridGenerica, informacaoVazia);
         }
      });
   }

   public ProviderGenerico<Produto, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Produto, String>(produtoAS, filtro);
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }

}
