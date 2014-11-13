package com.br.GrandeViaFitness.componentes;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class FeedBackPanelCustom extends FeedbackPanel
{

   private static final long serialVersionUID = -2640370090227218526L;

   public FeedBackPanelCustom(final String id)
   {
      super(id);
      setOutputMarkupId(true);
   }
   @Override
   public void renderHead(final IHeaderResponse response)
   {

      super.renderHead(response);
      final String script = "setTimeout(function(){$('#" + getMarkupId() + "').hide('slow')}, 15000);";
      response.render(OnDomReadyHeaderItem.forScript(script));
   }
}
