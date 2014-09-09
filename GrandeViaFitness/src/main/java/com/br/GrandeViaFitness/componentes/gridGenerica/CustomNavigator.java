package com.br.GrandeViaFitness.componentes.gridGenerica;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import com.br.GrandeViaFitness.componentes.ItemPerPageLink;

public class CustomNavigator<T, S> extends AbstractToolbar
{

   private static final long serialVersionUID = 1L;
   public static final List<Integer> DEFAULT_ITEMS_PER_PAGE_VALUES = Arrays.asList(5, 25, 50);
   private final List<Integer> itemsPerPageValues;
   private final DataTable<T, S> dataView;
   final WebMarkupContainer span;
   CustomPagingNavigator customPagingNavigator;

   /** Constructor
    *
    * @param table data table this toolbar will be attached to */
   public CustomNavigator(final DataTable<T, S> table)
   {
      super(table);
      dataView = table;
      itemsPerPageValues = CustomNavigator.DEFAULT_ITEMS_PER_PAGE_VALUES;
      span = new WebMarkupContainer("span");
      add(span);
      span.add(AttributeModifier.replace("colspan", new AbstractReadOnlyModel<String>()
      {
         private static final long serialVersionUID = 1L;

         @Override
         public String getObject()
         {
            return String.valueOf(table.getColumns().size());
         }
      }));
      customPagingNavigator = newPagingNavigator("navigator", table);
      span.add(customPagingNavigator);
      addLinksChangingItemsPerPageNumber();
   }

   private void addLinksChangingItemsPerPageNumber()
   {
      final ListView<Integer> itemsPerPageList = new ListView<Integer>("itemsPerPage", itemsPerPageValues)
      {
         private static final long serialVersionUID = 7824460215715231282L;

         @Override
         protected void populateItem(final ListItem<Integer> item)
         {
            final Link<T> itemPerPageLink =
               new ItemPerPageLink<T, S>("itemPerPageLink", dataView, customPagingNavigator, item.getModelObject());
            itemPerPageLink.add(new Label("itemsValue", item.getModel()));
            item.add(itemPerPageLink);
         }
      };

      span.add(itemsPerPageList);
   }

   protected CustomPagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table)
   {
      return new CustomPagingNavigator(navigatorId, table);
   }

   protected WebComponent newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table)
   {
      return new NavigatorLabel(navigatorId, table);
   }

   @Override
   protected void onConfigure()
   {
      super.onConfigure();
      // setVisible(getTable().getPageCount() > 1);
   }

}
