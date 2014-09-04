package com.br.GrandeViaFitness.componentes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class ProviderMemoria<T> implements IDataProvider<T>
{
    private static final long serialVersionUID = -2379057150630949730L;

    private final List<T> listaMemoria;
   private final Serializable filtro;

   public ProviderMemoria(final List<T> listaMemoria, final Serializable filtro)
    {
        this.listaMemoria = listaMemoria;
        this.filtro = filtro;
    }

    @Override
    public void detach()
    {

    }

    @Override
    public Iterator<? extends T> iterator(final long first, final long count)
    {
        return listaMemoria.iterator();
    }

    @Override
    public long size()
    {
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
