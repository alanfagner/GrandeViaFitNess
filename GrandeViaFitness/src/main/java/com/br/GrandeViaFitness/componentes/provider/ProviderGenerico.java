package com.br.GrandeViaFitness.componentes.provider;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.br.GrandeViaFitness.model.Entidade;

public class ProviderGenerico<T extends Serializable, S> extends SortableDataProvider<T, S>
{
   private static final long serialVersionUID = 3163256073992382494L;
   private final Provider<T> provider;
   private final Entidade Filtro;

   public ProviderGenerico(final Provider<T> provider, final Entidade filtro)
   {
      this.provider = provider;
      this.Filtro = filtro;
   }
   @Override
   public Iterator<? extends T> iterator(final long first, final long count)
   {
      return provider.buscaListaGrid(Filtro, first, count).iterator();
   }
   @Override
   public long size()
   {
      return provider.contadorListaGrid(Filtro);
   }
   @Override
   public IModel<T> model(final T object)
   {
      return new Model<T>(object);
   }
}
