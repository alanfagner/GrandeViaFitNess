package com.br.GrandeViaFitness.componentes;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.br.GrandeViaFitness.model.Entidade;

public class ProviderMemoria<T> implements IDataProvider<T>
{
    private static final long serialVersionUID = -2379057150630949730L;

    private final List<T> listaMemoria;
    private final Entidade filtro;

    public ProviderMemoria(final List<T> listaMemoria, final Entidade filtro)
    {
        this.listaMemoria = listaMemoria;
        this.filtro = filtro;
    }

    @Override
    public void detach()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterator<? extends T> iterator(final long first, final long count)
    {
        // TODO Auto-generated method stub
        return listaMemoria.iterator();
    }

    @Override
    public long size()
    {
        // TODO Auto-generated method stub
        return listaMemoria.size();
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
