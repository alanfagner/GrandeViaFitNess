package com.br.GrandeViaFitness.utilitario;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

public class BaseUtil
{
   private final static String pastaCss = "./common/css/";
   private final static String pastaJs = "./common/js/";
   private final static String pastaThemaAzul = "./common/themAzul/";
   private final static String pastaMobileBlue = "./common/MobileBlue/";

   public static void geralMobile(final IHeaderResponse response, final boolean themaVermelho)
   {
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.mobile.custom.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.cycle.all.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.maskedinput.min.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jQuery.ui.datepicker.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.ui.datepicker.mobile.js");

      BaseUtil.addCss(response, BaseUtil.pastaCss + "960.css");
      BaseUtil.addCss(response, BaseUtil.pastaCss + "mobile.css");
      BaseUtil.addCss(response, BaseUtil.pastaCss + "jquery.mobile.custom.structure.css");
      BaseUtil.addCss(response, BaseUtil.pastaMobileBlue + "GrandeViaFitnessBlue.css");
      BaseUtil.addCss(response, BaseUtil.pastaMobileBlue + "jquery.mobile.icons.min.css");
      BaseUtil.addCss(response, BaseUtil.pastaMobileBlue + "jquery.ui.datepicker.mobile.css");

   }

   public static void geral(final IHeaderResponse response, final boolean themaVermelho)
   {
      BaseUtil.addCss(response, BaseUtil.pastaCss + "960.css");
      BaseUtil.addCss(response, BaseUtil.pastaCss + "myCss.css");

      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery-1.11.1.min.js");
      BaseUtil.addCss(response, BaseUtil.pastaCss + "jquery.dataTables.min.css");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "relogio.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.maskedinput.min.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "addTheme.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.maskMoney.js");
      BaseUtil.addJS(response, BaseUtil.pastaJs + "jquery.dataTables.min.js");

      BaseUtil.adicionarThema(response, BaseUtil.pastaThemaAzul);
   }

   private static void adicionarThema(final IHeaderResponse response, final String thema)
   {

      BaseUtil.addCss(response, thema + "jquery-ui.min.css");
      BaseUtil.addCss(response, thema + "jquery-ui.structure.min.css");
      BaseUtil.addCss(response, thema + "jquery-ui.theme.min.css");
      BaseUtil.addJS(response, thema + "jquery-ui.min.js");

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
