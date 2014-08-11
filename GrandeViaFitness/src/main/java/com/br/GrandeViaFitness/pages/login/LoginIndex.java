package com.br.GrandeViaFitness.pages.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.br.GrandeViaFitness.model.User;
import com.br.GrandeViaFitness.pages.login.basePageLogin.BasePageLogin;

public class LoginIndex extends BasePageLogin
{
   private static final long serialVersionUID = -140731152008355046L;

   private final Form<User> formLogin;
   private final Button botaoEnviar;
   private final User auxUser;

   public LoginIndex()
   {
      auxUser = new User();
        auxUser.setCpf("alan.f.goncalves@hotmail.com");
      auxUser.setPassword("123456");
      formLogin = new Form<User>("formulario", new CompoundPropertyModel<User>(auxUser))
      {
         private static final long serialVersionUID = 5237188163889525420L;

         @Override
         protected void onSubmit()
         {
            final AuthenticatedWebSession session = AuthenticatedWebSession.get();
            getSession().getId();
            if (session.signIn(auxUser.getCpf(), auxUser.getPassword()))
            {
               setDefaultResponsePageIfNecessary();
            }
            else
            {

            }
         }
      };
      formLogin.add(new TextField<String>("cpf"));
      formLogin.add(new TextField<String>("password"));
      botaoEnviar = new Button("btnEnviar")
      {
         private static final long serialVersionUID = -4123179816742735831L;

         @Override
         public void onSubmit()
         {

         }
      };
      botaoEnviar.setOutputMarkupId(true);
      formLogin.add(botaoEnviar);
      add(formLogin);
   }

   private void setDefaultResponsePageIfNecessary()
   {
      setResponsePage(getApplication().getHomePage());
   }
}
