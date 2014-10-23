package com.br.GrandeViaFitness.pages.visao;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Entidade;

public class HomePageForm extends FormularioBase<Entidade>
{
   private static final long serialVersionUID = 2942839205672245952L;

   public HomePageForm(final String id)
   {
      super(id);
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {

      super.renderHead(response);
      final Url url = Url.parse("./common/js/jquery-1.11.1.min.js");
      response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(url)));

   }
}
