package com.br.GrandeViaFitness.pages.login.basePage;

import java.util.Arrays;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import com.br.GrandeViaFitness.Utilitario.BaseUtil;
import com.br.GrandeViaFitness.componentes.ProviderMemoria;

public class BasePage extends WebPage
{

   private static final long serialVersionUID = -5753783437944591926L;
   private ProviderMemoria<String> providerMemoria;

   public BasePage()
   {
      final Form<String> forMenu = new Form<String>("formMenu");

      add(forMenu);
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

   public ProviderMemoria<String> criaProvider()
   {
      providerMemoria = new ProviderMemoria<String>(Arrays.asList("teste", "normal"), null);
      return providerMemoria;
   }

}
