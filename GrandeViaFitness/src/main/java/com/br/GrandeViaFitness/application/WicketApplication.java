package com.br.GrandeViaFitness.application;

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

import com.br.GrandeViaFitness.dao.imp.UserDaoImp;
import com.br.GrandeViaFitness.model.User;
import com.br.GrandeViaFitness.pages.login.LoginIndex;
import com.br.GrandeViaFitness.pages.visao.HomePage;
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
   UserDaoImp userDao;

   String[] langs;

   @Override
   public Class<HomePage> getHomePage()
   {
      return HomePage.class;
   }

   @Override
   public void init()
   {

      super.init();

      getApplicationSettings().setPageExpiredErrorPage(HomePage.class);

      SpringComponentInjector springComponentInjector = null;

      // this is for test
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



      langs = new String[]{"it", "en"};
      mountPage("HomePage.html", HomePage.class);
      mountPage("Login.html", LoginIndex.class);

   }

   private void loadSampleDataIfNoExists()
   {

      final String email = "user@test.it";
      final User user = userDao.findByEmail(email);

      if (user == null)
      {
         final User luigi =
            new User(email, new Md5PasswordEncoder().encodePassword("123456", null), "ROLE_USER",
               "ROLE_ADMIN");
         luigi.setCpf("34520184827");
         userDao.save(luigi);
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

   public void setUserDao(final UserDaoImp userDao)
   {
      this.userDao = userDao;
   }

   public String[] getLangs()
   {
      return langs;
   }

}
