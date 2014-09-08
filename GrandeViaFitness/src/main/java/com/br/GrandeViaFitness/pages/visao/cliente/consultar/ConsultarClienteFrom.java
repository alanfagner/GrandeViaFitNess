package com.br.GrandeViaFitness.pages.visao.cliente.consultar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;

public class ConsultarClienteFrom extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 553958619270523962L;

   private DataGridGenerica<Pessoa, String> gridGenerica;
   private Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   @SpringBean
   private PessoaAS pessoaAS;

   public ConsultarClienteFrom(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();
      criaGridCliente();

   }

   private void criaGridCliente()
   {
      final List<IColumn<Pessoa, String>> columns = new ArrayList<IColumn<Pessoa, String>>();
      final List<AjaxLink<Pessoa>> listBotoes = new ArrayList<AjaxLink<Pessoa>>();

      listBotoes.add(new AjaxLink<Pessoa>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            target.add(gridGenerica);
         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });
      listBotoes.add(new AjaxLink<Pessoa>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            getModelObject();
         }
      });
      columns.add(new PropertyColumn<Pessoa, String>(new Model<String>("Codigo"), "codigo")
      {

         private static final long serialVersionUID = 3580594711515520158L;

         /*@Override
         public String getCssClass()
         {
            return "numeric";
         }*/
      });
      columns.add(new PropertyColumn<Pessoa, String>(new Model<String>("Nome"), "nomePessoa", "nomePessoa"));
      columns.add(new PropertyColumn<Pessoa, String>(new Model<String>("CPF"), "cpfPessoa", "cpfPessoa"));
      columns.add(new PropertyColumn<Pessoa, String>(new Model<String>("Email"), "emailPessoa"));
      columns.add(new AbstractColumn<Pessoa, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public void populateItem(final Item<ICellPopulator<Pessoa>> cellItem, final String componentId, final IModel<Pessoa> entidade)
         {
            cellItem.add(new ActionButtonPanel<Pessoa>(componentId, entidade, listBotoes));
         }

      });
      gridGenerica =
 new DataGridGenerica<Pessoa, String>("table", columns, getProviderGenerico(), 5);
      gridGenerica.setOutputMarkupId(true);
      gridGenerica.setItemsPerPage(5);

      addOrReplace(gridGenerica);
   }

   private void criaBotoes()
   {
      final Button btnNovoCliente = new Button("btnNovoCliente")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new CadastrarAlterarClienteIndex());
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
   public ProviderGenerico<Pessoa, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<Pessoa, String>(pessoaAS, filtro);
      }
      return providerGenerico;
   }
}
