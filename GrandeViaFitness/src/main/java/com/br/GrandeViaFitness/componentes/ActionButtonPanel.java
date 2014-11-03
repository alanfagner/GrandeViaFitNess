package com.br.GrandeViaFitness.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ActionButtonPanel<T> extends Panel
{
   private static final long serialVersionUID = 1324759387448672575L;
   private final T entidade;
   List<AjaxLink<T>> listBotoes = new ArrayList<AjaxLink<T>>();

   public ActionButtonPanel(final String id, final IModel<T> model, final List<AjaxLink<T>> listBotoes, final Boolean mostrarExcluir,
      final Boolean mostrarVisualizar)
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
         final AjaxSubmitLink externalLink = new AjaxSubmitLink("Link")
         {
            private static final long serialVersionUID = 249147107071691478L;

            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
            {
               link.setDefaultModel(new Model((Serializable) model.getObject()));
               link.onClick(target);
            }

            @Override
            public void renderHead(final IHeaderResponse response)
            {
               if (link.getId().equals("Excluir"))
               {
                  final String script =
                     "$('#" + getMarkupId() + "').button({icons : { primary : 'ui-icon-circle-close' }, text : false });   ";
                  response.render(OnDomReadyHeaderItem.forScript(script));
               }
               else
               {
                  if (link.getId().equals("Visualizar"))
                  {
                  final String script =
                     "$('#" + getMarkupId() + "').button({icons : { primary : 'ui-icon-circle-zoomin' }, text : false });   ";
                  response.render(OnDomReadyHeaderItem.forScript(script));
                  }
                  else
                  {
                     final String script =
                        "$('#" + getMarkupId() + "').button({icons : { primary : 'ui-icon-circle-check' }, text : false });   ";
                     response.render(OnDomReadyHeaderItem.forScript(script));
                  }
               }

            }

         };

         if (link.getId().equals("Excluir"))
         {
            externalLink.setVisibilityAllowed(mostrarExcluir);
         }
         else
         {
            externalLink.setVisibilityAllowed(mostrarVisualizar);
         }

         externalLink.setOutputMarkupPlaceholderTag(true);
         externalLink.add(new AttributeAppender("class", link.getId()));
         list.add(externalLink);
         view.add(list);
      }
   }
}
