package com.br.GrandeViaFitness.pages.login.basePage;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import com.br.GrandeViaFitness.AS.PessoaAS;
import com.br.GrandeViaFitness.Componentes.InformacaoAlerta;
import com.br.GrandeViaFitness.Model.Pessoa;
import com.br.GrandeViaFitness.Utilitario.BaseUtil;
import com.br.GrandeViaFitness.pages.visao.aparelhos.ConsultarAparelhosIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.consultar.ConsultarClienteIndex;

public class BasePage extends WebPage
{

   private static final long serialVersionUID = -5753783437944591926L;

   private Pessoa usuarioLogado;
   public FeedbackPanel feedback;
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

      criaFeedBack();
      addOrReplace(menu, containerNome, feedback);
      criaMenus();
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

      final Link<String> consultarAparelho = new Link<String>("consultarAparelhos")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new ConsultarAparelhosIndex());
         }
      };
      menu.add(consultarCliente, consultarAparelho);
   }

   public void criaFeedBack()
   {
      feedback = new InformacaoAlerta("feedback");
      feedback.setFilter(new ContainerFeedbackMessageFilter(this));
      feedback.setOutputMarkupPlaceholderTag(true);
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
