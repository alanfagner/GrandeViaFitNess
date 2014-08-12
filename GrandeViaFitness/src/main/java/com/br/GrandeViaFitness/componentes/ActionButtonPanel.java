package com.br.GrandeViaFitness.componentes;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class ActionButtonPanel<T> extends Panel
{
   private static final long serialVersionUID = 1324759387448672575L;
   private final T entidade;
   List<AjaxLink<T>> listBotoes = new ArrayList<AjaxLink<T>>();

   public ActionButtonPanel(final String id, final IModel<T> model,
      final List<AjaxLink<T>> listBotoes)
   {
      super(id);
      this.entidade = model.getObject();
      final RepeatingView view = new RepeatingView("LinkList");
      view.setOutputMarkupPlaceholderTag(true);
      this.add(view);
      this.listBotoes = listBotoes;

      for (final AjaxLink<T> link : this.listBotoes)
      {
         final WebMarkupContainer list = new WebMarkupContainer(view.newChildId());
         final AjaxLink<T> externalLink =
            new AjaxLink<T>("Link", new PropertyModel<T>(this, "entidade"))
            {
               private static final long serialVersionUID = 249147107071691478L;

               @Override
               public void onClick(final AjaxRequestTarget target)
               {
                  link.setModel(this.getModel());
                  link.onClick(target);
               }

            };
         externalLink.add(new Label("label", link.getId()));
         list.add(externalLink);
         view.add(list);
      }
   }
}
