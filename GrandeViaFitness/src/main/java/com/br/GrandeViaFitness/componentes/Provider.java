package com.br.GrandeViaFitness.componentes;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.br.GrandeViaFitness.model.Entidade;

public class Provider<T> extends SortableDataProvider<T, String>
{

    private static final long serialVersionUID = 6491965295375421637L;

    private final ProviderGenerico<T> providerGenerico;
    private final Entidade filtro;

    public Provider(final ProviderGenerico<T> providerGenerico, final Entidade filtro)
    {
        this.providerGenerico = providerGenerico;
        this.filtro = filtro;
        setSort("bairro", SortOrder.ASCENDING);
    }

    @Override
    public void detach()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterator<? extends T> iterator(final long first, final long count)
    {
        return providerGenerico.buscaListaGrid(filtro, first, count).iterator();
    }

    @Override
    public long size()
    {
        return providerGenerico.contadorListaGrid(filtro);
    }

    @Override
    public IModel<T> model(final T object)
    {

        return new LoadableDetachableModel<T>(object)
        {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            protected T load()
            {

                return (T) filtro;
            }
        };
    }

}
