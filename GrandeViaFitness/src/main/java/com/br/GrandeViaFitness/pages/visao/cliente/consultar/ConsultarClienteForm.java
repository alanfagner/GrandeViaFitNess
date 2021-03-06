package com.br.GrandeViaFitness.pages.visao.cliente.consultar;

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
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.PessoaAS;
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
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.visualizar.VisualizarClienteIndex;
import com.googlecode.wicket.jquery.ui.form.button.ConfirmAjaxButton;

public class ConsultarClienteForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 553958619270523962L;

   private DataGridGenerica<Pessoa, String> gridGenerica;
   private final Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   @SpringBean
   private PessoaAS pessoaAS;
   private FeedBackPanelCustom feedBack;

   private TextField<Long> campoCodigo;

   private TextField<String> campoNome;

   private TextField<String> campoCpf;

   private TextField<String> campoEmail;

   private ConfirmAjaxButton confirma;

   private AjaxButtonCustom botaoPesquisar;
   private WebMarkupContainer informacaoVazia;

   private ConfirmAjaxButtonCustom<Pessoa> modal;

   public ConsultarClienteForm(final String id)
   {
      super(id, new CompoundPropertyModel<Pessoa>(new Pessoa()));
      filtro = getModelObject();
      inicializar();
   }

   private void inicializar()
   {
      criaInformacao();
      criaBotoes();
      criaCampos();
      criaGridCliente();
      criaModal();
      criaFeedBack();
   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);

   }

   private void criaModal()
   {
      modal = new ConfirmAjaxButtonCustom<Pessoa>("modal", "Excluir", "Excluir", "Confirma a exclusão do cliente selecionado?")
      {
         private static final long serialVersionUID = -1878575807000071842L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (pessoaAS.verificaHistorico(getEntidade()))
            {
               pessoaAS.excluirPessoa(getEntidade());
               success(Mensagem.recuperaMensagem(Mensagem.M02, "Cliente"));
               atualizaTela(target, gridGenerica, feedBack);
            }
            else
            {
               getSession().error(Mensagem.recuperaMensagem(Mensagem.M019, "Cliente"));
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

   private void criaInformacao()
   {
      informacaoVazia = new WebMarkupContainer("containerVazio");
      informacaoVazia.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVazia);

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
            modal.setEntidade(getModelObject());
            modal.button.onClick(target);
         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });
      listaBotoes.add(new AjaxLink<Pessoa>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new VisualizarClienteIndex(getModelObject()));
         }
      });
      columns.add(DataGridGenerica.criaColunarPessoa("Código", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarPessoa("Nome", "nomePessoa", true, 30));
      columns.add(DataGridGenerica.criaColunarPessoa("CPF", "cpfMascara", true, 30));
      columns.add(DataGridGenerica.criaColunarPessoa("Email", "emailPessoa", true, 30));
      columns.add(DataGridGenerica.criaColunarPessoa("Permissão", "cargoEnum.descricao", true, 10));
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
            cellItem.add(new ActionButtonPanel<Pessoa>(componentId, entidade, listaBotoes, true, true));

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
      addOrReplace(gridGenerica);
   }

   private void criaCampos()
   {
      campoCodigo = new TextField<Long>("codigo");
      campoNome = new TextField<String>("nomePessoa");
      campoCpf = new TextField<String>("cpfPessoa");
      campoEmail = new TextField<String>("emailPessoa");

      campoCodigo.setLabel(new Model<String>("Codigo"));
      campoNome.setLabel(new Model<String>("Nome"));
      campoCpf.setLabel(new Model<String>("Cpf"));
      campoEmail.setLabel(new Model<String>("Email"));

      addOrReplace(campoCodigo, campoNome, campoCpf, campoEmail);

   }

   private void criaBotoes()
   {
      botaoPesquisar = new AjaxButtonCustom("btnPesquisar")
      {
         private static final long serialVersionUID = -2955360194609797122L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            atualizaTela(target, gridGenerica, informacaoVazia);
         }
      };

      final AjaxButtonCustom btnNovoCliente = new AjaxButtonCustom("btnNovoCliente")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarClienteIndex());
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
      addOrReplace(btnNovoCliente, btnVoltar, botaoPesquisar);
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
}
