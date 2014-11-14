package com.br.GrandeViaFitness.pages.visao.venda.detalharVenda;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.RlProdutoVendaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.visao.relatorio.produto.RelatorioProdutoIndex;

public class DetalharVendaForm extends FormularioBase<Venda>
{
   private static final long serialVersionUID = -7379937077286526567L;
   private DataGridGenerica<RlProdutoVenda, String> gridGenericaRlProdutoVenda;
   private ProviderGenerico<RlProdutoVenda, String> providerVenda;
   private RlProdutoVenda rlProdutoVenda;
   @SpringBean
   private RlProdutoVendaAS rlProdutoVendaAS;

   public DetalharVendaForm(final String id, final Venda venda)
   {
      super(id, new CompoundPropertyModel<Venda>(venda));
      inicializar();
   }

   private void inicializar()
   {
      criaLabel();
      criaGrid();
      criaBotao();
   }

   private void criaBotao()
   {
      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = -1395336213410602497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new RelatorioProdutoIndex());

         }
      });

   }

   private void criaGrid()
   {
      final List<IColumn<RlProdutoVenda, String>> columns = new ArrayList<IColumn<RlProdutoVenda, String>>();
      final List<AjaxLink<RlProdutoVenda>> listaBotoes = new ArrayList<AjaxLink<RlProdutoVenda>>();
      listaBotoes.add(new AjaxLink<RlProdutoVenda>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {

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
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Nome Produto", "produto.nomeProduto", true, 40));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Quantidade", "quantidadeVendido", true, 10));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Valor Unitario", "produto.valorMascara", true, 20));
      columns.add(DataGridGenerica.criaColunarRlProdutoVenda("Valor Total Produto", "valorTotal", true, 20));

      final AbstractColumn<RlProdutoVenda, String> opcoes = new AbstractColumn<RlProdutoVenda, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "gridColunaInvi";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<RlProdutoVenda>> cellItem, final String componentId,
            final IModel<RlProdutoVenda> entidade)
         {
            cellItem.add(new ActionButtonPanel<RlProdutoVenda>(componentId, entidade, listaBotoes, false, false));

         }
      };
      columns.add(opcoes);
      gridGenericaRlProdutoVenda = new DataGridGenerica<RlProdutoVenda, String>("tableProdutoVenda", columns, getProviderVenda(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
         }
      };
      gridGenericaRlProdutoVenda.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenericaRlProdutoVenda);

   }

   public ProviderGenerico<RlProdutoVenda, String> getProviderVenda()
   {
      if (providerVenda == null)
      {
         rlProdutoVenda = new RlProdutoVenda();
         rlProdutoVenda.setVenda(getModelObject());
         providerVenda = new ProviderGenerico<RlProdutoVenda, String>(rlProdutoVendaAS, rlProdutoVenda);
         providerVenda.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerVenda;
   }

   private void criaLabel()
   {
      addOrReplace(new Label("nomePessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa().getNomePessoa() : ""));
      addOrReplace(new Label("cpfPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa().getCpfMascara() : ""));
      addOrReplace(new Label("emailPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa().getEmailPessoa() : ""));
      addOrReplace(new Label("celularPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa().getNumTelMascraca()
         : ""));
      addOrReplace(new Label("campoDataVenda", getModelObject() != null ? getModelObject().getDataFormatada() : ""));

      addOrReplace(new Label("valorTotal", getModelObject().getValorTotal() != null ? getModelObject().getValorFormatado() : ""));

   }

}
