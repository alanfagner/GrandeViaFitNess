package com.br.GrandeViaFitness.componentes;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class BotoesGrid<T, S>
{
   private final List<AjaxLink<T>> listBotoes;

   public BotoesGrid()
   {
      listBotoes = new ArrayList<AjaxLink<T>>();

   }

   public AbstractColumn<T, S> criaListaBotoes()
   {
      return criaColunaOpcoes(listBotoes);
   }

   public void add(final AjaxLink<T> link)
   {
      listBotoes.add(link);
   }


   public AbstractColumn<T, S> criaColunaOpcoes(final List<AjaxLink<T>> listBotoes)
   {

      return new AbstractColumn<T, S>(new Model<String>("Opções"))
      {
         private static final long serialVersionUID = -3102670641136395641L;

         @Override
         public String getCssClass()
         {
            return "tam5";
         }

         @Override
         public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId, final IModel<T> entidade)
         {
            cellItem.add(new ActionButtonPanel<T>(componentId, entidade, listBotoes));
         }
      };
   }
}
