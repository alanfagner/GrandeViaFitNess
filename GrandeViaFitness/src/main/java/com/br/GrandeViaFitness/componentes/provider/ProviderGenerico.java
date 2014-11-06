package com.br.GrandeViaFitness.componentes.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.model.Entidade;

public class ProviderGenerico<T extends Serializable, S> extends SortableDataProvider<T, S>
{
   private static final long serialVersionUID = 3163256073992382494L;
   private final Provider<T> provider;
   private final Entidade Filtro;
   private ParametrosOrdenacao ordernar;
   private List<T> listaRegistros;

   public ProviderGenerico(final Provider<T> provider, final Entidade filtro)
   {
      this.provider = provider;
      this.Filtro = filtro;
      listaRegistros = new ArrayList<T>();
   }
   @Override
   public Iterator<? extends T> iterator(final long first, final long count)
   {
      if (getSort() != null)
      {
         ordernar = isOrdena(getSort().getProperty().toString(), getSort().isAscending());
      }
      listaRegistros = provider.buscaListaGrid(Filtro, first, count, ordernar);
      return listaRegistros.iterator();
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

   public ParametrosOrdenacao isOrdena(final String coluna, final Boolean isAscending)
   {
      return new ParametrosOrdenacao(coluna, isAscending);
   }

   public ParametrosOrdenacao getOrdernar()
   {
      return ordernar;
   }

   public void setOrdernar(final ParametrosOrdenacao ordernar)
   {
      this.ordernar = ordernar;
   }

   public List<T> getListaRegistros()
   {
      return listaRegistros;
   }

   public void setListaRegistros(final List<T> listaRegistros)
   {
      this.listaRegistros = listaRegistros;
   }

}
