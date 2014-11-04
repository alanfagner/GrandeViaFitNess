package com.br.GrandeViaFitness.componentes;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

public class AjaxButtonCustom extends AjaxButton
{
   private static final long serialVersionUID = -9082541040348918892L;

   public AjaxButtonCustom(final String id)
   {
      super(id);
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      final String script = "$('#" + getMarkupId() + "').button(); ";
      response.render(OnDomReadyHeaderItem.forScript(script));
   }

}
