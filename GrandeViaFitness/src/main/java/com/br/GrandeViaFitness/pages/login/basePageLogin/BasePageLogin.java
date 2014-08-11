package com.br.GrandeViaFitness.pages.login.basePageLogin;

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
        // addCss(response, "./common/css/geral.css");
        /*
         * // addCss(response,
         * "./common/css/jquery.mobile.structure-1.4.3.min.css"); //
         * addCss(response, "./common/css/jquery.mobile.icons.min.css");
         * addCss(response, "./common/css/themes/GrandeViaFitness.css");
         * addJS(response, "./common/js/jquery-1.11.1.min.js"); //
         * addJS(response, "./common/js/jquery.mobile-1.4.3.min.js");
         */
        adicionarthemas(response);
    }
  private void adicionarthemas(final IHeaderResponse response){
        addCss(response, "./common/css/960.css");
        addCss(response, "./common/thema/jquery-ui.css");
        addJS(response, "./common/thema/external/jquery/jquery.js");
        addJS(response, "./common/thema/jquery-ui.js");

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

}
