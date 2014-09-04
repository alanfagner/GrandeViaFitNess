package com.br.GrandeViaFitness.pages.login.basePage;

import java.util.Arrays;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import com.br.GrandeViaFitness.Utilitario.BaseUtil;
import com.br.GrandeViaFitness.componentes.ProviderMemoria;
import com.br.GrandeViaFitness.model.Pessoa;

public class BasePage extends WebPage
{

   private static final long serialVersionUID = -5753783437944591926L;
   private ProviderMemoria<String> providerMemoria;

   private static Pessoa usuarioLogado = new Pessoa();

   public BasePage()
   {
      final WebMarkupContainer menu = new WebMarkupContainer("containerMenu");
      final WebMarkupContainer containerNome = new WebMarkupContainer("containerUsuario");
      final Label nomeUsuario = new Label("nomeUsuario", BasePage.usuarioLogado.getNomePessoa());
      menu.setOutputMarkupPlaceholderTag(true);
      containerNome.setOutputMarkupPlaceholderTag(true);
      containerNome.add(nomeUsuario);
      if (BasePage.usuarioLogado.getCodigo() == null)
      {
         menu.setVisibilityAllowed(false);
         containerNome.setVisibilityAllowed(false);

      }
      else
      {
         menu.setVisibilityAllowed(true);
         containerNome.setVisibilityAllowed(true);
      }
      addOrReplace(menu, containerNome);
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      BaseUtil.geral(response, true);
   }

   public ProviderMemoria<String> criaProvider()
   {
      providerMemoria = new ProviderMemoria<String>(Arrays.asList("teste", "normal"), null);
      return providerMemoria;
   }

   public static Pessoa getUsuarioLogado()
   {
      return BasePage.usuarioLogado;
   }

   public static void setUsuarioLogado(final Pessoa usuarioLogado)
   {
      BasePage.usuarioLogado = usuarioLogado;
   }
}
