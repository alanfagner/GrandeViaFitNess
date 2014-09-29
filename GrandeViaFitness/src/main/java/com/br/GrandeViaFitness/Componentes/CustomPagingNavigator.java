package com.br.GrandeViaFitness.Componentes;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;

public class CustomPagingNavigator<T, S> extends AbstractToolbar
{

   private static final long serialVersionUID = 5555160808947621763L;
   public static final String NAVIGATION_ID = "navegar";
   public static final List<Integer> DEFAULT_ITEMS_PER_PAGE_VALUES = Arrays.asList(5, 25, 50);

   private PagingNavigation pagingNavigation;
   private final DataTable<T, S> dataView;
   private final List<Integer> itemsPerPageValues;
   private WebMarkupContainer pagingLinksContainer;

   public CustomPagingNavigator(final DataTable<T, S> dataView)
   {
      this(dataView, null, CustomPagingNavigator.DEFAULT_ITEMS_PER_PAGE_VALUES);
   }

   public CustomPagingNavigator(final DataTable<T, S> dataView, final List<Integer> itemsPerPageValues)
   {
      this(dataView, null, itemsPerPageValues);
   }

   public CustomPagingNavigator(final DataTable<T, S> dataView, final IPagingLabelProvider labelProvider)
   {
      this(dataView, labelProvider, CustomPagingNavigator.DEFAULT_ITEMS_PER_PAGE_VALUES);
   }

   public CustomPagingNavigator(final DataTable<T, S> dataView, final IPagingLabelProvider labelProvider,
      final List<Integer> itemsPerPageValues)
   {
      super(dataView);
      this.dataView = dataView;
      this.itemsPerPageValues = itemsPerPageValues;
      addContainerWithPagingLinks();
      addLinksChangingItemsPerPageNumber();
   }

   @Override
   public boolean isVisible()
   {
      return dataView.getItemCount() > 0;
   }

   private void addContainerWithPagingLinks()
   {

      pagingLinksContainer = new WebMarkupContainer("pagingLinksContainer")
      {
         private static final long serialVersionUID = -1623367084037346791L;

         @Override
         public boolean isVisible()
         {
            return dataView.getPageCount() > 1;
         }
      };

      pagingNavigation = newNavigation(dataView);
      pagingNavigation.add(new AttributeAppender("class", "paginate_button"));
      pagingLinksContainer.add(pagingNavigation);
      pagingLinksContainer.add(newPagingNavigationLink("first", dataView, 0).add(new TitleAppender("PagingNavigator.first")));
      pagingLinksContainer.add(newPagingNavigationIncrementLink("prev", dataView, -1).add(new TitleAppender("PagingNavigator.previous")));
      pagingLinksContainer.add(newPagingNavigationIncrementLink("next", dataView, 1).add(new TitleAppender("PagingNavigator.next")));
      pagingLinksContainer.add(newPagingNavigationLink("last", dataView, -1).add(new TitleAppender("PagingNavigator.last")));

      add(pagingLinksContainer);
   }

   protected PagingNavigation newNavigation(final IPageable pageable)
   {
      return new PagingNavigation(CustomPagingNavigator.NAVIGATION_ID, pageable);
   }

   protected AbstractLink newPagingNavigationIncrementLink(final String id, final IPageable pageable, final int increment)
   {
      return new PagingNavigationIncrementLink<Void>(id, pageable, increment);
   }

   protected AbstractLink newPagingNavigationLink(final String id, final IPageable pageable, final int pageNumber)
   {
      return new PagingNavigationLink<Void>(id, pageable, pageNumber);
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
               new ItemPerPageLink<T, S>("itemPerPageLink", dataView, pagingLinksContainer, item.getModelObject());
            itemPerPageLink.add(new Label("itemsValue", item.getModel()));
            item.add(itemPerPageLink);
         }
      };

      add(itemsPerPageList);
   }

   public final PagingNavigation getPagingNavigation()
   {
      return pagingNavigation;
   }

   private final class TitleAppender extends AbstractAjaxBehavior
   {
      private static final long serialVersionUID = 1L;

      private final String resourceKey;

      public TitleAppender(final String resourceKey)
      {
         this.resourceKey = resourceKey;
      }

      @Override
      protected void onComponentTag(final ComponentTag tag)
      {
         tag.put("title", CustomPagingNavigator.this.getString(resourceKey));
      }

      @Override
      public void onRequest()
      {
         // TODO Auto-generated method stub

      }

   }
}
