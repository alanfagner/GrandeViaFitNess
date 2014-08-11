package com.br.GrandeViaFitness.componentes;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class IDataSorteProvider<T extends Serializable, S> extends SortableDataProvider<T, S>
{
   private static final long serialVersionUID = -1998280577730813461L;

   private final List<T> listaARetornar;

   @Override
   public Iterator<? extends T> iterator(final long first, final long count)
   {
      if (this.getSort() != null)
      {
         Collections.sort(this.getListaARetornar(), new Comparator<T>()
         {
            @Override
            public int compare(final T o1, final T o2)
            {
               return 0;
            }
         });
      }
      return this.listaARetornar.subList((int) first, (int) first + (int) count).iterator();
   }

   @Override
   public long size()
   {
      return this.listaARetornar.size();
   }

   @Override
   public IModel<T> model(final T object)
   {
      return new Model<T>(object);
   }

   public List<T> getListaARetornar()
   {
      return this.listaARetornar;
   }

   public IDataSorteProvider(final List<T> listaARetornar)
   {
      this.listaARetornar = listaARetornar;
   }

}
