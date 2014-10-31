package com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.pages.visao.cliente.visualizar.VisualizarClienteIndex;

public class EfetuarVendaForm extends FormularioBase<Venda>
{
   private static final long serialVersionUID = -2379657526732075658L;

   @SpringBean
   private PessoaAS pessoaAS;

   private DataGridGenerica<Pessoa, String> gridGenerica;
   private WebMarkupContainer informacaoVaziaPessoa;
   private ProviderGenerico<Pessoa, String> providerGenericoPessoa;
   private Pessoa filtroPessoa;

   public EfetuarVendaForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      filtroPessoa = new Pessoa();
      criaGridPessoa();
      criaInformacao();

   }

   private void criaInformacao()
   {
      informacaoVaziaPessoa = new WebMarkupContainer("containerVazioPessoa");
      informacaoVaziaPessoa.setOutputMarkupPlaceholderTag(true);
      addOrReplace(informacaoVaziaPessoa);

   }

   private void criaGridPessoa()
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
         protected void onConfigure()
         {
            setOutputMarkupId(true);
            setVisible(false);
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
      columns.add(DataGridGenerica.criaColunarPessoa("Codigo", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunarPessoa("Nome", "nomePessoa", true, 40));
      columns.add(DataGridGenerica.criaColunarPessoa("CPF", "cpfPessoa", true, 10));
      columns.add(DataGridGenerica.criaColunarPessoa("Email", "emailPessoa", true, 40));
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
      gridGenerica = new DataGridGenerica<Pessoa, String>("tablePessoa", columns, getProviderGenericoPessoa(), 5)
      {
         private static final long serialVersionUID = -2837712007974126400L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(getItemCount() > 0);
            informacaoVaziaPessoa.setVisibilityAllowed(getItemCount() == 0);
         }
      };
      gridGenerica.setOutputMarkupPlaceholderTag(true);
      addOrReplace(gridGenerica);

   }

   public ProviderGenerico<Pessoa, String> getProviderGenericoPessoa()
   {
      if (providerGenericoPessoa == null)
      {
         providerGenericoPessoa = new ProviderGenerico<Pessoa, String>(pessoaAS, filtroPessoa);
         providerGenericoPessoa.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenericoPessoa;
   }
}
