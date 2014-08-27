package com.br.GrandeViaFitness.pages.login.basePageLogin;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

public class BasePageLogin extends WebPage
{
   private static final long serialVersionUID = -5753783437944591926L;

   public BasePageLogin()
   {

   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      adicionarthemas(response);
   }

   private void adicionarthemas(final IHeaderResponse response)
   {
      addCss(response, "./common/css/960.css");
      addCss(response, "./common/css/myCss.css");

      // addCss(response, "./common/thema/jquery-ui.structure.css");
      // addCss(response, "./common/thema/jquery-ui.theme.css");
      // addCss(response, "./common/thema/jquery-ui.css");
      // addJS(response, "./common/thema/external/jquery/jquery.js");
      // addJS(response, "./common/thema/jquery-ui.js");
      // addJS(response, "./common/js/jqueryThema.js");

      addJS(response, "./common/js/jquery-1.11.1.min.js");
      addJS(response, "./common/js/relogio.js");

   }

   private void addJS(final IHeaderResponse response, final String js)
   {
      final Url url = Url.parse(js);
      response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(url)));
   }

   private void addCss(final IHeaderResponse response, final String css)
   {
      final Url url = Url.parse(css);
      response.render(CssHeaderItem.forReference(new UrlResourceReference(url)));
   }

   public void atualizaTela(final AjaxRequestTarget target)
   {
      target.appendJavaScript("$.addScriptCss();");
   };

}
