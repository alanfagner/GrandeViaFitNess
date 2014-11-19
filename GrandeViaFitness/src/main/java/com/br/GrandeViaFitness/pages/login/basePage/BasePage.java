package com.br.GrandeViaFitness.pages.login.basePage;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
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
import com.br.GrandeViaFitness.pages.visao.mensalidade.pagamento.PagamentoIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.produto.consultar.ConsultarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.relatorio.mensalidade.RelatorioMensalidadeIndex;
import com.br.GrandeViaFitness.pages.visao.relatorio.produto.RelatorioProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda.EfetuarVendaIndex;
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

   private Link<String> buttonlogout;

   private Boolean logado = false;

   private Link<String> buttonMobile;

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
         logado = true;
      }
      addOrReplace(menu, containerNome);
      criaMenus();

      if (getUsuarioLogado().getCargoEnum() != null && getUsuarioLogado().getCargoEnum() != PermissaoEnum.ADMINISTRADOR)
      {
         setResponsePage(new MobileHomeIndex());
      }
   }

   private void criaMenus()
   {
      final Link<String> pagamentoMensalidade = new Link<String>("pagamentoMenslidade")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new PagamentoIndex());
         }
      };

      final Link<String> consultarCliente = new Link<String>("consultarCliente")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new ConsultarClienteIndex());
         }
      };

      final Link<String> consultarVenda = new Link<String>("relatorioVenda")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new RelatorioProdutoIndex());
         }
      };

      final Link<String> efetuarVenda = new Link<String>("efetuarVenda")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new EfetuarVendaIndex());
         }
      };

      final Link<String> consultarProduto = new Link<String>("consultarProduto")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new ConsultarProdutoIndex());
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

      final Link<String> relatorioMensalidade = new Link<String>("relatorioMensalidade")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new RelatorioMensalidadeIndex());
         }
      };
      buttonlogout = new Link<String>("sair")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            getSession().invalidate();
            AuthenticatedWebSession.get().signOut();
         }

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').button({ icons : { primary :'ui-icon-power'}, text : true});   ";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      };

      buttonMobile = new Link<String>("mobile")
      {
         private static final long serialVersionUID = -633142704625312739L;

         @Override
         public void onClick()
         {
            setResponsePage(new MobileHomeIndex());
         }

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#" + getMarkupId() + "').button({ icons : { primary :'ui-icon-transferthick-e-w'}, text : true});   ";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }
      };
      addOrReplace(buttonlogout, buttonMobile);
      buttonlogout.setOutputMarkupId(true);
      buttonlogout.setVisibilityAllowed(logado);
      buttonMobile.setOutputMarkupId(true);
      buttonMobile.setVisibilityAllowed(logado);
      menu.add(consultarCliente, consultarAparelho, consultarProduto, efetuarVenda, pagamentoMensalidade, relatorioMensalidade,
         consultarVenda);
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
         if (SecurityContextHolder.getContext().getAuthentication() != null
            && !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
         {
            usuarioLogado = pessoaAS.buscaPessoaPorCpf(SecurityContextHolder.getContext().getAuthentication().getName());
         }
      }
      return usuarioLogado;
   }
}
