package com.br.GrandeViaFitness.pages.visao.venda.fecharVenda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.as.VendaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenericoMemoria;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda.EfetuarVendaIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class FechaVendaForm extends FormularioBase<Venda>
{
   private static final long serialVersionUID = 8986075591893989669L;
   @SpringBean
   private PessoaAS pessoaAS;
   @SpringBean
   private VendaAS vendaAS;
   private Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   private DataGridGenerica<RlProdutoVenda, String> gridGenericaRlProdutoVenda;
   private ProviderGenericoMemoria<RlProdutoVenda, String> providerVenda;
   private final List<RlProdutoVenda> listaVenda;
   private WebMarkupContainer informacaoVazia;
   private DataGridGenerica<Pessoa, String> gridGenerica;
   private FeedBackPanelCustom feedBack;
   private TextField<Date> campoDataVenda;
   private TextField<String> campoNome;
   private TextField<String> campoCpf;
   private WebMarkupContainer containerGrid;
   private WebMarkupContainer containerGridDados;

   public FechaVendaForm(final String id, final List<RlProdutoVenda> listaVenda)
   {
      super(id, new CompoundPropertyModel<Venda>(new Venda()));
      this.listaVenda = listaVenda;
      incicializar();
   }

   private void incicializar()
   {
      filtro = new Pessoa();
      criaContainergrid();
      criaGridVenda();
      criaInformacao();
      criaGridCliente();
      criaFeedBack();
      criaCampos();
      criaBotoes();
      criaLabel();
      criaValorTotal();

   }

   private void criaValorTotal()
   {
      BigDecimal valorTotal = new BigDecimal(0);
      for (final RlProdutoVenda auxProdutoVenda : listaVenda)
      {
         valorTotal = valorTotal.add(auxProdutoVenda.getProduto().getValorProduto().multiply( new BigDecimal(auxProdutoVenda.getQuantidadeVendido())));
      }
      valorTotal.setScale(2, RoundingMode.HALF_UP);
      final DecimalFormat df = new DecimalFormat(",##0.00");
      FechaVendaForm.this.getModelObject().setValorTotal(valorTotal);
      addOrReplace(new Label("valorTotal", df.format(valorTotal)));

   }

   private void criaLabel()
   {
      containerGridDados.addOrReplace(new Label("nomePessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getNomePessoa() : ""));
      containerGridDados.addOrReplace(new Label("cpfPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getCpfPessoa() : ""));
      containerGridDados.addOrReplace(new Label("emailPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getEmailPessoa() : ""));
      containerGridDados.addOrReplace(new Label("celularPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getNumeroCelulaPessoa()
         : ""));
   }

   private void criaContainergrid()
   {
      containerGrid = new WebMarkupContainer("containerGrid");
      containerGrid.setOutputMarkupPlaceholderTag(true);

      containerGridDados = new WebMarkupContainer("containerGridDados");
      containerGridDados.setOutputMarkupPlaceholderTag(true);
      containerGridDados.setVisibilityAllowed(false);
      add(containerGrid, containerGridDados);

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("btnCompra")
      {
         private static final long serialVersionUID = -3530397776219222313L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if(validaCampos()){
               vendaAS.salvarVenda(listaVenda, FechaVendaForm.this.getModelObject());
               getSession().success(Mensagem.recuperaMensagem(Mensagem.M013));
               setResponsePage(new EfetuarVendaIndex());
            }
            target.add(feedBack);

         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            target.add(feedBack);
         }
      });

      addOrReplace(new AjaxButtonCustom("btnVoltar")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new EfetuarVendaIndex(FechaVendaForm.this.listaVenda));
         }
      });

      containerGrid.addOrReplace(new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            gridGenerica.size();
            target.add(gridGenerica, informacaoVazia);
         }
      });

      containerGrid.addOrReplace(new AjaxButtonCustom("btnLimpar")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new FechaVendaIndex(listaVenda));
         }
      });

      containerGridDados.addOrReplace(new AjaxButtonCustom("btnEscolherCliente")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            containerGrid.setVisibilityAllowed(true);
            containerGridDados.setVisibilityAllowed(false);
            FechaVendaForm.this.getModelObject().setPessoa(null);
            target.add(containerGrid, containerGridDados);
         }
      });

   }

   private boolean validaCampos()
   {
      Boolean valida = true;

      if (FechaVendaForm.this.getModelObject().getPessoa() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M012, "a venda"));
         valida = false;
      }

      if (campoDataVenda.getModelObject() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Data"));
         valida = false;
      }
      else
      {
         FechaVendaForm.this.getModelObject().setDataVenda(Util.converteData(campoDataVenda.getModelObject()));
      }
      return valida;
   }

   private void criaCampos()
   {
      campoDataVenda = new TextField<Date>("campoDataVenda", new Model<Date>())
      {

         private static final long serialVersionUID = -7583345623555027277L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').datepicker( $.datepicker.regional[ 'pt-BR' ] );";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }

      };

      campoNome = new TextField<String>("nomePessoa", new PropertyModel<String>(filtro, "nomePessoa"));
      campoCpf = new TextField<String>("cpfPessoa", new PropertyModel<String>(filtro, "cpfMascara"))
      {
         private static final long serialVersionUID = 5130791194139092536L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').mask('999.999.999-99'); ";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      };
      containerGrid.add(campoNome, campoCpf);
      addOrReplace(campoDataVenda);
   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);

   }

   private void criaGridCliente()
   {
      final List<IColumn<Pessoa, String>> columns = new ArrayList<IColumn<Pessoa, String>>();
      final List<AjaxLink<Pessoa>> listaBotoes = new ArrayList<AjaxLink<Pessoa>>();
      listaBotoes.add(new AjaxLink<Pessoa>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {

         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });
      listaBotoes.add(new AjaxLink<Pessoa>("Adicionar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            FechaVendaForm.this.getModelObject().setPessoa(getModelObject());
            criaLabel();
            containerGrid.setVisibilityAllowed(false);
            containerGridDados.setVisibilityAllowed(true);
            target.add(containerGrid, containerGridDados);
         }
      });
      columns.add(DataGridGenerica.criaColunarPessoa("Código", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarPessoa("Nome", "nomePessoa", true, 40));
      columns.add(DataGridGenerica.criaColunarPessoa("CPF", "cpfMascara", true, 20));
      columns.add(DataGridGenerica.criaColunarPessoa("Email", "emailPessoa", true, 30));
      columns.add(new AbstractColumn<Pessoa, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<Pessoa>> cellItem, final String componentId, final IModel<Pessoa> entidade)
         {
            cellItem.add(new ActionButtonPanel<Pessoa>(componentId, entidade, listaBotoes, false, true));

         }
      });
      gridGenerica = new DataGridGenerica<Pessoa, String>("table", columns, getProviderGenerico(), 5)
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
      containerGrid.addOrReplace(gridGenerica);
   }

   public ProviderGenerico<Pessoa, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Pessoa, String>(pessoaAS, filtro);
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      containerGrid.addOrReplace(informacaoVazia);

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
            cellItem.add(new ActionButtonPanel<RlProdutoVenda>(componentId, entidade, listaBotoes, false, false));

         }
      });
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

   public ProviderGenericoMemoria<RlProdutoVenda, String> getProviderVenda()
   {
      if (providerVenda == null)
      {
         providerVenda = new ProviderGenericoMemoria<RlProdutoVenda, String>(listaVenda);
      }
      return providerVenda;
   }
}
