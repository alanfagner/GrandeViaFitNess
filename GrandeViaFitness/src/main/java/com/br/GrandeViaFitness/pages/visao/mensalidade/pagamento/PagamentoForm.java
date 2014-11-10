package com.br.GrandeViaFitness.pages.visao.mensalidade.pagamento;

import java.math.BigDecimal;
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
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
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
import com.br.GrandeViaFitness.utilitario.Util;

public class PagamentoForm extends FormularioBase<Mensalidade>
{
   private static final long serialVersionUID = 5071706619828094567L;
   private WebMarkupContainer containerGrid;
   private WebMarkupContainer containerGridDados;
   private FeedBackPanelCustom feedBack;
   private DataGridGenerica<Pessoa, String> gridGenerica;
   @SpringBean
   private PessoaAS pessoaAS;
   @SpringBean
   private MensalidadeAS mensalidadeAS;
   private Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   private WebMarkupContainer informacaoVazia;
   private TextField<Date> campoDataVenda;
   private TextField<String> campoNome;
   private TextField<String> campoCpf;
   private DropDownChoice<MesReferenciaEnum> comboMesReferencia;
   private TextField<Integer> campoAnoReferencia;
   private TextField<String> campoValorPagamento;

   public PagamentoForm(final String id)
   {
      super(id, new CompoundPropertyModel<Mensalidade>(new Mensalidade()));
      inicializar();
   }

   private void inicializar()
   {
      filtro = new Pessoa();
      criaContainergrid();
      criaInformacao();
      criaGridCliente();
      criaFeedBack();
      criaCampos();
      criaBotoes();
      criaLabel();
      criaComboBox();

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

   private void criaBotoes()
   {
      containerGridDados.addOrReplace(new AjaxButtonCustom("btnEscolherCliente")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            containerGrid.setVisibilityAllowed(true);
            containerGridDados.setVisibilityAllowed(false);
            PagamentoForm.this.getModelObject().setPessoa(null);
            target.add(containerGrid, containerGridDados);
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
            setResponsePage(new PagamentoIndex());
         }
      });

      addOrReplace(new AjaxButtonCustom("btnSalvar")
      {
         private static final long serialVersionUID = -1257990070248878837L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampos())
            {
               PagamentoForm.this.getModelObject().setAnoReferencia(campoAnoReferencia.getModelObject());
               if (validaPagamentoExistente())
               {
                  try
                  {
                     PagamentoForm.this.getModelObject().setDataPagamento(Util.converteData(campoDataVenda.getModelObject()));
                  }
                  catch (final Exception e)
                  {
                     getSession().error("Data não existe!");
                     target.add(feedBack);
                     return;
                  }
                  PagamentoForm.this.getModelObject().setValorPago(new BigDecimal(campoValorPagamento.getModelObject().replace(",", "")));
                  mensalidadeAS.save(PagamentoForm.this.getModelObject());
                  getSession().success(Mensagem.recuperaMensagem(Mensagem.M014));
                  setResponsePage(new PagamentoIndex());
               }
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
            setResponsePage(new HomePageIndex());
         }
      });

   }

   private boolean validaPagamentoExistente()
   {
      Boolean valido = true;
      if (mensalidadeAS.buscaMensalidade(getModelObject()) > 0)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M015));
         valido = false;
      }
      return valido;
   }

   private boolean validaCampos()
   {
      Boolean valido = true;
      if (PagamentoForm.this.getModelObject().getPessoa() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M012, "o pagamento da mensalidade"));
         valido = false;
      }

      if (comboMesReferencia.getModelObject() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Mês de Referencia"));
         valido = false;
      }

      if (campoDataVenda.getModelObject() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Data"));
         valido = false;
      }

      if (campoAnoReferencia.getModelObject() == null)
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Ano"));
         valido = false;
      }

      if (campoValorPagamento.getModelObject() == null || campoValorPagamento.getModelObject().equals(""))
      {
         getSession().error(Mensagem.recuperaMensagem(Mensagem.M04, "Valor Pagamento"));
         valido = false;
      }

      return valido;
   }

   private void criaLabel()
   {
      containerGridDados.addOrReplace(new Label("nomePessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getNomePessoa() : ""));
      containerGridDados.addOrReplace(new Label("cpfPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getCpfMascara() : ""));
      containerGridDados.addOrReplace(new Label("emailPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getEmailPessoa() : ""));
      containerGridDados.addOrReplace(new Label("celularPessoa", getModelObject().getPessoa() != null ? getModelObject().getPessoa()
         .getNumTelMascraca() : ""));
   }

   private void criaCampos()
   {
      campoDataVenda = new TextField<Date>("campoDataVenda", new Model<Date>())
      {

         private static final long serialVersionUID = -7583345623555027277L;

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').datepicker();";
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

      campoAnoReferencia = new TextField<Integer>("anoReferencia");
      campoValorPagamento = new TextField<String>("valorPago", new Model<String>());
      containerGrid.add(campoNome, campoCpf);
      addOrReplace(campoDataVenda, campoAnoReferencia, campoValorPagamento);
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

            PagamentoForm.this.getModelObject().setPessoa(getModelObject());
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

   private void criaContainergrid()
   {
      containerGrid = new WebMarkupContainer("containerGrid");
      containerGrid.setOutputMarkupPlaceholderTag(true);

      containerGridDados = new WebMarkupContainer("containerGridDados");
      containerGridDados.setOutputMarkupPlaceholderTag(true);
      containerGridDados.setVisibilityAllowed(false);
      add(containerGrid, containerGridDados);

   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);

   }

}
