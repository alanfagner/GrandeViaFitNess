package com.br.GrandeViaFitness.pages.visao;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
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
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.br.GrandeViaFitness.as.EnderecoAS;
import com.br.GrandeViaFitness.componentes.ActionPanel;
import com.br.GrandeViaFitness.componentes.Provider;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.pages.visao.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class HomePage extends BasePage
{
    private Provider<Endereco> providerEndeProvider;
    private Endereco endereco;
    @SpringBean
    private EnderecoAS enderecoAS;
    private static final long serialVersionUID = -2280598124596767977L;
    List<IColumn<Endereco, String>> columns = new ArrayList<IColumn<Endereco, String>>();
    public HomePage(final PageParameters parameters)
    {
        final List<AjaxButton> listaBotes = new ArrayList<AjaxButton>();
        listaBotes.add(new AjaxButton("select")
        {
            private static final long serialVersionUID = 6144692741544847861L;
        });

        columns.add(new AbstractColumn<Endereco, String>(new Model<String>("Opcões"))
        {
            private static final long serialVersionUID = -3102670641136395641L;

            @Override
            public void populateItem(final Item<ICellPopulator<Endereco>> cellItem, final String componentId,
                    final IModel<Endereco> model)
            {
                cellItem.add(new ActionPanel<Endereco>(componentId, model)
                {
                    private static final long serialVersionUID = -493349872952934325L;

                    @Override
                    protected void onClickLink(final Endereco entidade)
                    {
                        // TODO Auto-generated method stub

                    }
                });
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Nome da Rua"), "logradouro", "logradouro")
        {
            private static final long serialVersionUID = 8912637927959976436L;

            @Override
            public String getCssClass()
            {
                return "numeric";
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Nome da Rua"), "logradouro", "logradouro")
        {
            private static final long serialVersionUID = 8912637927959976436L;

            @Override
            public String getCssClass()
            {
                return "numeric";
            }
        });

        columns.add(new PropertyColumn<Endereco, String>(new Model<String>("Nome do bairro"), "bairro", "bairro"));

        add(new DefaultDataTable<Endereco, String>("table", columns, getProviderEndeProvider(), 5));

    }

    public Provider<Endereco> getProviderEndeProvider()
    {
        if (providerEndeProvider == null)
        {
            endereco = new Endereco();
            providerEndeProvider = new Provider<Endereco>(enderecoAS, endereco);
        }
        return providerEndeProvider;
    }

}
