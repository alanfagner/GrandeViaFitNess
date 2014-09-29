package com.br.GrandeViaFitness.Componentes;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;

public class ItemPerPageLink<T, S> extends Link<T>
{
   private static final long serialVersionUID = 7859552953754456912L;
   private final int itemsPerPage;
   private final DataTable<T, S> dataTable;
   private final WebMarkupContainer pagingLinksContainer;

   public ItemPerPageLink(final String id, final DataTable<T, S> dataTable, final WebMarkupContainer pagingLinksContainer,
      final int itemsPerPageValue)
   {
      super(id);
      this.dataTable = dataTable;
      this.pagingLinksContainer = pagingLinksContainer;
      this.itemsPerPage = itemsPerPageValue;
      setEnabled(itemsPerPageValue != dataTable.getItemsPerPage());
   }

   @Override
   public void onClick()
   {
      dataTable.setItemsPerPage(itemsPerPage);
   }

   @Override
   protected void onComponentTag(final ComponentTag tag)
   {
      super.onComponentTag(tag);
      tag.put("title", itemsPerPage);
   }

}
