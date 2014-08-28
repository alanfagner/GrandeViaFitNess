package com.br.GrandeViaFitness.pages.login.basePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import com.br.GrandeViaFitness.Utilitario.BaseUtil;

public class BasePage extends WebPage
{

   private static final long serialVersionUID = -5753783437944591926L;

   public BasePage()
   {

   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      BaseUtil.geral(response, true);
   }

   public void atualizaTela(final AjaxRequestTarget target)
   {
      target.appendJavaScript("$.addScriptCss();");
   };

}
