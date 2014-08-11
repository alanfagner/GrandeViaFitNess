package com.br.GrandeViaFitness.pages.visao;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.br.GrandeViaFitness.componentes.ActionButtonPanel;
import com.br.GrandeViaFitness.componentes.IDataSorteProvider;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.pages.visao.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class HomePage extends BasePage
{

    private static final long serialVersionUID = -2280598124596767977L;
    private IDataSorteProvider<Endereco, String> dataProvider;
    private final List<AjaxLink<Endereco>> listBotoes = new ArrayList<AjaxLink<Endereco>>();
    private DefaultDataTable<Endereco, String> gridGenerica;

    public HomePage(final PageParameters parameters)
    {
        final List<IColumn<Endereco, String>> columns = new ArrayList<IColumn<Endereco, String>>();

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

        columns.add(new AbstractColumn<Endereco, String>(new Model<String>("Opções"))
        {
            private static final long serialVersionUID = -3102670641136395641L;

            @Override
            public void populateItem(final Item<ICellPopulator<Endereco>> cellItem, final String componentId,
                    final IModel<Endereco> entidade)
            {

                cellItem.add(new ActionButtonPanel<Endereco>(componentId, entidade, listBotoes));
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Codigo"), "codigo")
        {

            private static final long serialVersionUID = 3580594711515520158L;

            @Override
            public String getCssClass()
            {
                return "numeric";
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Logradouro"), "logradouro", "logradouro"));

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Bairro"), "bairro", "bairro")
        {
            /**
          *
          */
            private static final long serialVersionUID = 3774551391770421047L;

            @Override
            public String getCssClass()
            {
                return "last-name";
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Cidade"), "cidade"));
        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Cep"), "cep"));

        gridGenerica = new DefaultDataTable<Endereco, String>("table", columns, getDataProvider(), 5);
        gridGenerica.setOutputMarkupId(true);
        this.add(gridGenerica);
    }

    public IDataSorteProvider<Endereco, String> getDataProvider()
    {
        if (dataProvider == null)
        {
            final List<Endereco> listaAPassar = new ArrayList<Endereco>();
            for (int i = 0; i < 20; i++)
            {
                final Endereco ende = new Endereco();
                ende.setLogradouro("Alamenda" + i);
                ende.setEstado("SP");
                ende.setCodigo(i);
                ende.setCidade("Araraquara");
                ende.setCep(14811060);
                ende.setBairro("Vila Xavier" + i);
                listaAPassar.add(ende);
            }
            dataProvider = new IDataSorteProvider<Endereco, String>(listaAPassar);
        }
        return dataProvider;
    }
}
