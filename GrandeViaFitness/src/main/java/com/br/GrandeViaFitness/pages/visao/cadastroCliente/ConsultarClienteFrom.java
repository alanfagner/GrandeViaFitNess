package com.br.GrandeViaFitness.pages.visao.cadastroCliente;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;

public class ConsultarClienteFrom extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 553958619270523962L;


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
      final List<IColumn<Endereco, String>> columns = new ArrayList<IColumn<Endereco, String>>();
      final List<AjaxLink<Endereco>> listBotoes = new ArrayList<AjaxLink<Endereco>>();
      listBotoes.add(new AjaxLink<Endereco>("Excluir")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            dataProvider.getListaARetornar().remove(getModelObject());
            dataProvider.size();
            target.add(gridGenerica);
         }

         @Override
         protected void onBeforeRender()
         {
            super.onBeforeRender();
         }
      });

      listBotoes.add(new AjaxLink<Endereco>("Visualizar")
      {
         private static final long serialVersionUID = -2007593370707695822L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            getModelObject();
         }
      });
      columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Codigo"), "codigo")
      {

         private static final long serialVersionUID = 3580594711515520158L;

         /*@Override
         public String getCssClass()
         {
            return "numeric";
         }*/
      });

      columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Logradouro"), "logradouro", "logradouro"));

      columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Bairro"), "bairro", "bairro"));

      columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Cidade"), "cidade"));
      columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Cep"), "cep"));
      columns.add(new AbstractColumn<Endereco, String>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public void populateItem(final Item<ICellPopulator<Endereco>> cellItem, final String componentId, final IModel<Endereco> entidade)
         {

            cellItem.add(new ActionButtonPanel<Endereco>(componentId, entidade, listBotoes));

         }

      });

      gridGenerica = new DefaultDataTable<Endereco, String>("table", columns, getDataProvider(), 5);
      gridGenerica.setOutputMarkupId(true);
      this.add(gridGenerica);

   }

   private void criaBotoes()
   {
      final Button btnNovoCliente = new Button("btnNovoCliente")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            // setResponsePage(new CadastrarAlterarClienteIndex());
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

}
