package com.br.GrandeViaFitness.application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.enumUtil.PermissaoEnum;
import com.br.GrandeViaFitness.enumUtil.SexoEnum;
import com.br.GrandeViaFitness.model.Authority;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.login.LoginIndex;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.consultar.ConsultarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.visualizar.VisualizarClienteIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar.CadastrarAlterarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.consultar.ConsultarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.visualizar.VisualizarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.mensalidade.pagamento.PagamentoIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio.MobileExecutarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.Login.LoginMobileIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileDetalharExercicio.MobileDetalharExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio.MobileTipoExercicoIndex;
import com.br.GrandeViaFitness.pages.visao.produto.cadastrar.CadastrarAlterarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.produto.consultar.ConsultarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.produto.visualizar.VisualizarProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.relatorio.mensalidade.RelatorioMensalidadeIndex;
import com.br.GrandeViaFitness.pages.visao.relatorio.produto.RelatorioProdutoIndex;
import com.br.GrandeViaFitness.pages.visao.venda.detalharVenda.DetalharVendaIndex;
import com.br.GrandeViaFitness.pages.visao.venda.efetuarVenda.EfetuarVendaIndex;
import com.br.GrandeViaFitness.pages.visao.venda.fecharVenda.FechaVendaIndex;
import com.br.GrandeViaFitness.security.MyAuthenticatedWebSession;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * @see com.br.GrandeViaFitness.Start#main(String[])
 */
@Component
public class WicketApplication extends AuthenticatedWebApplication implements
   ApplicationContextAware
{
   Logger logger = LoggerFactory.getLogger(WicketApplication.class);

   @Autowired
   ApplicationContext applicationContext;

   @SpringBean
   PessoaAS pessoaAS;
   String[] langs;

   @Override
   public Class<HomePageIndex> getHomePage()
   {
      return HomePageIndex.class;
   }

   @Override
   public void init()
   {
      super.init();
      getDebugSettings().setAjaxDebugModeEnabled(false);
      getApplicationSettings().setPageExpiredErrorPage(HomePageIndex.class);
      SpringComponentInjector springComponentInjector = null;
      if (applicationContext != null)
      {
         springComponentInjector = new SpringComponentInjector(this, applicationContext, true);
      }
      else
      {
         springComponentInjector = new SpringComponentInjector(this);
      }
      springComponentInjector.inject(this);
      getComponentInstantiationListeners().add(springComponentInjector);

      langs = new String[]{"pt", "pt-br"};
      mountPage("Principal.html", HomePageIndex.class);
      mountPage("Login.html", LoginIndex.class);

      mountPage("ConsultarCliente.html", ConsultarClienteIndex.class);
      mountPage("ConsultarAtividade.html", ConsultarExercicioIndex.class);
      mountPage("ConsultarProduto.html", ConsultarProdutoIndex.class);

      mountPage("CadastrarAlterarCliente.html", CadastrarAlterarClienteIndex.class);
      mountPage("CadastrarAlterarAtividade.html", CadastrarAlterarExercicioIndex.class);
      mountPage("CadastrarAlterarProduto.html", CadastrarAlterarProdutoIndex.class);

      mountPage("VisualizarCliente.html", VisualizarClienteIndex.class);
      mountPage("VisualizarAtividade.html", VisualizarExercicioIndex.class);
      mountPage("VisualizarProduto.html", VisualizarProdutoIndex.class);

      mountPage("Venda.html", EfetuarVendaIndex.class);
      mountPage("FechaVenda.html", FechaVendaIndex.class);

      mountPage("Pagamento.html", PagamentoIndex.class);

      mountPage("RelatorioMensalidade.html", RelatorioMensalidadeIndex.class);
      mountPage("RelatorioProduto.html", RelatorioProdutoIndex.class);

      mountPage("DetalharVenda.html", DetalharVendaIndex.class);

      mountPage("Mobile.html", LoginMobileIndex.class);
      mountPage("HomeMobile.html", MobileHomeIndex.class);

      mountPage("VisualizarListaMembroMobile.html", MobileMembroCorpoIndex.class);
      mountPage("VisualizarListaAtividadeMobile.html", MobileTipoExercicoIndex.class);

      mountPage("ExecutarAtividadeMobile.html", MobileExecutarExercicioIndex.class);
      mountPage("DetalharAtividadeMobile.html", MobileDetalharExercicioIndex.class);

      getApplicationSettings().setPageExpiredErrorPage(LoginIndex.class);

      try
      {
         loadSampleDataIfNoExists();
      }
      catch (final ParseException e)
      {
         e.printStackTrace();
      }
      this.getMarkupSettings().setStripWicketTags(true);
   }

   private void loadSampleDataIfNoExists() throws ParseException
   {
      if (pessoaAS.getPessoaServico().buscaPessoaPorCpf("34520184827") == null)
      {
         final Pessoa pessoa = new Pessoa();
         pessoa.setAuthority( new Authority());
         pessoa.setEndereco(new Endereco());
         pessoa.getAuthority().setAuthority("R_ADM");
         pessoa.setNomePessoa("Alan Fagner Gonçalves");
         pessoa.setCpfPessoa("34520184827");
         pessoa.setEmailPessoa("alan.f.goncalves@hotamil.com");
         pessoa.setNumeroCelulaPessoa("16997578380");
         pessoa.setNumeroResidencial(743);
         pessoa.setSexo(SexoEnum.M);
         pessoa.setCargoEnum(PermissaoEnum.ADMINISTRADOR);
         final DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
         pessoa.setDataNascimentoPessoa(formatter.parse("07/27/1985"));
         final Calendar calendar = Calendar.getInstance();
         calendar.setTime(pessoa.getDataNascimentoPessoa());
         pessoa.setSenhaPessoa(new Md5PasswordEncoder().encodePassword(pessoa.getCpfPessoa().substring(0, 3) + calendar.get(1), null));
         pessoa.getEndereco().setCep("14811066");
         pessoa.getEndereco().setCidade("Araraquara");
         pessoa.getEndereco().setBairro("Vila Xavier");
         pessoa.getEndereco().setEstado("SP");
         pessoa.getEndereco().setLogradouro("Alameda Paulista");
         pessoaAS.saveInicial(pessoa);
      }
   }

   @Override
   protected Class<? extends WebPage> getSignInPageClass()
   {
      return LoginIndex.class;
   }

   @Override
   protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
   {
      return MyAuthenticatedWebSession.class;
   }

   @Override
   public void setApplicationContext(final ApplicationContext applicationContext)
      throws BeansException
   {
      this.applicationContext = applicationContext;
   }

   public String[] getLangs()
   {
      return langs;
   }

}
