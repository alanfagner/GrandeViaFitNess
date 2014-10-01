package com.br.GrandeViaFitness.componentes.provider;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import com.br.GrandeViaFitness.model.Pessoa;

public class ProviderGrid extends ListDataProvider<Pessoa> implements ISortStateLocator<String>
{

   private static final long serialVersionUID = 2988135533254378729L;
   private final SingleSortState<String> state = new SingleSortState<String>();

   public ProviderGrid(final List<Pessoa> listaPessoa)
   {
      super(listaPessoa);
   }


   @Override
   protected List<Pessoa> getData()
   {
      final List<Pessoa> list = super.getData();
      final SortParam<String> param = this.state.getSort();

      if (param != null)
      {
         Collections.sort(list, new ProductComparator(param.getProperty(), param.isAscending()));
      }

      return list;
   }

   @Override
   public ISortState<String> getSortState()
   {
      return this.state;
   }

   static class ProductComparator implements Comparator<Pessoa>, Serializable
   {
      private static final long serialVersionUID = 1L;

      private final String property;
      private final boolean ascending;

      public ProductComparator(final String property, final boolean ascending)
      {
         this.property = property;
         this.ascending = ascending;
      }

      @Override
      public int compare(final Pessoa p1, final Pessoa p2)
      {
         final Object o1 = PropertyResolver.getValue(this.property, p1);
         final Object o2 = PropertyResolver.getValue(this.property, p2);

         if (o1 != null && o2 != null)
         {
            final Comparable<Object> c1 = ProductComparator.toComparable(o1);
            final Comparable<Object> c2 = ProductComparator.toComparable(o2);

            return c1.compareTo(c2) * (this.ascending ? 1 : -1);
         }

         return 0;
      }

      @SuppressWarnings("unchecked")
      private static Comparable<Object> toComparable(final Object o)
      {
         if (o instanceof Comparable<?>)
         {
            return (Comparable<Object>) o;
         }

         throw new WicketRuntimeException("Object should be a Comparable");
      }
   }

}
