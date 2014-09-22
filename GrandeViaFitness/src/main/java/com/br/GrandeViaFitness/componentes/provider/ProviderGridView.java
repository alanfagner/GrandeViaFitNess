package com.br.GrandeViaFitness.componentes.provider;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.model.Entidade;

public class ProviderGridView<T extends Serializable> implements IDataProvider<T>
{
   private static final long serialVersionUID = -291130998511908228L;
   private final Provider<T> provider;
   private final Entidade entidade;

   public ProviderGridView(final Entidade entidade, final Provider<T> provider)
   {
      this.entidade = entidade;
      this.provider = provider;
   }
   @Override
   public void detach()
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Iterator<? extends T> iterator(final long first, final long count)
   {
      return provider.buscaListaGrid(entidade, first, count, new ParametrosOrdenacao("coluna", true)).iterator();
   }

   @Override
   public long size()
   {
      return provider.contadorListaGrid(entidade);
   }

   @Override
   public IModel<T> model(final T object)
   {
      return new Model<T>(object);
   }

}
