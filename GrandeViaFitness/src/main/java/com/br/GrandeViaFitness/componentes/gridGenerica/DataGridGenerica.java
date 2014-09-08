package com.br.GrandeViaFitness.componentes.gridGenerica;

import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import com.br.GrandeViaFitness.componentes.CustomPagingNavigator;

public class DataGridGenerica<T, S> extends DataTable<T, S>
{

   public DataGridGenerica(final String id, final List<? extends IColumn<T, S>> columns, final ISortableDataProvider<T, S> dataProvider,
      final long rowsPerPage)
   {
      super(id, columns, dataProvider, rowsPerPage);
      addTopToolbar(new CustomPagingNavigator<T, S>(this));
      addTopToolbar(new HeadersToolbar<S>(this, dataProvider));
      addBottomToolbar(new NoRecordsToolbar(this));
   }

   @Override
   protected Item<T> newRowItem(final String id, final int index, final IModel<T> model)
   {
      return new OddEvenItem<T>(id, index, model);
   }

   private static final long serialVersionUID = -1418047019008118408L;

}
