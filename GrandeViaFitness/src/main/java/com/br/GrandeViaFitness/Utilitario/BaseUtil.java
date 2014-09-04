package com.br.GrandeViaFitness.Utilitario;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

public class BaseUtil
{
   private final static String pastaCss = "./common/css/";
   private final static String pastaJs = "./common/js/";
   private final static String pastaThemaRed = "./common/themeRed/";

   public static void geral(final IHeaderResponse response, final boolean themaVermelho)
   {
      BaseUtil.addCss(response, BaseUtil.pastaCss + "960.css");
      BaseUtil.addCss(response, BaseUtil.pastaCss + "myCss.css");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "relogio.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery-1.11.1.min.js");

      if(themaVermelho){
         BaseUtil.AdicionarthemaVermelho(response);
      }
   }

   private static void AdicionarthemaVermelho(final IHeaderResponse response)
   {
      BaseUtil.addCss(response, BaseUtil.pastaThemaRed + "jquery-ui.css");
      BaseUtil.addCss(response, BaseUtil.pastaThemaRed + "jquery-ui.structure.min.css");
      BaseUtil.addCss(response, BaseUtil.pastaThemaRed + "jquery-ui.theme.min.css");
      BaseUtil.addJS(response, BaseUtil.pastaThemaRed + "jquery-ui.min.js");
      BaseUtil.addJS(response, BaseUtil.pastaThemaRed + "addTheme.js");
   }

   private static void addJS(final IHeaderResponse response, final String js)
   {
      final Url url = Url.parse(js);
      response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(url)));
   }

   private static void addCss(final IHeaderResponse response, final String css)
   {
      final Url url = Url.parse(css);
      response.render(CssHeaderItem.forReference(new UrlResourceReference(url)));
   }

}
