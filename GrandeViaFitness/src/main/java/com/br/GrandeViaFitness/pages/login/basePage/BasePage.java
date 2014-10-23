package com.br.GrandeViaFitness.pages.login.basePage;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.enumUtil.PermissaoEnum;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.cliente.consultar.ConsultarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.consultar.ConsultarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.utilitario.BaseUtil;

public class BasePage extends WebPage
{

   private static final long serialVersionUID = -5753783437944591926L;

   private Pessoa usuarioLogado;

   public static final String FORMULARIO = "formulario";
   private final WebMarkupContainer menu;
   private final WebMarkupContainer containerNome;
   @SpringBean
   private PessoaAS pessoaAS;

   public BasePage()
   {
      menu = new WebMarkupContainer("containerMenu");
      containerNome = new WebMarkupContainer("containerUsuario");
      final Label nomeUsuario = new Label("nomeUsuario", getUsuarioLogado().getNomePessoa());
      menu.setOutputMarkupPlaceholderTag(true);
      containerNome.setOutputMarkupPlaceholderTag(true);
      containerNome.add(nomeUsuario);

      if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
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
      criaMenus();

      if (getUsuarioLogado().getCargoEnum() != null && getUsuarioLogado().getCargoEnum() != PermissaoEnum.FUNCIONARIO)
      {
         setResponsePage(new MobileHomeIndex());
      }
   }

   private void criaMenus()
   {
      final Link<String> consultarCliente = new Link<String>("consultarCliente")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new ConsultarClienteIndex());
         }
      };

      final Link<String> consultarAparelho = new Link<String>("consultarExercicio")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new ConsultarExercicioIndex());
         }
      };
      menu.add(consultarCliente, consultarAparelho);
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      BaseUtil.geral(response, false);
   }

   public Pessoa getUsuarioLogado()
   {
      if (usuarioLogado == null)
      {
         usuarioLogado = new Pessoa();
         if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
         {
            usuarioLogado = pessoaAS.buscaPessoaPorCpf(SecurityContextHolder.getContext().getAuthentication().getName());
         }
      }
      return usuarioLogado;
   }
}
