package com.br.GrandeViaFitness.pages.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.model.User;
import com.br.GrandeViaFitness.pages.login.basePageLogin.BasePageLogin;

public class LoginIndex extends BasePageLogin
{
   private static final long serialVersionUID = -140731152008355046L;

   private final Form<User> formLogin;
   private final AjaxButton botaoEnviar;
   private final User auxUser;

   public LoginIndex()
   {
      auxUser = new User();
      auxUser.setCpf("alan.f.goncalves@hotmail.com");
      auxUser.setPassword("123456");
      formLogin = new Form<User>("formulario", new CompoundPropertyModel<User>(auxUser));
      formLogin.add(new TextField<String>("cpf"));
      formLogin.add(new TextField<String>("password"));
      botaoEnviar = new AjaxButton("btnEnviar")
      {
         private static final long serialVersionUID = 5503520007522761497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            final User user = (User) form.getModelObject();
            final AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(user.getCpf(), user.getPassword()))
            {
               getModelObject();
               setDefaultResponsePageIfNecessary();
            }
            else
            {
               target.add(form);
               atualizaTela(target);
            }
            form.getModelObject();
         }

         @Override
         protected void onConfigure()
         {

         }

      };

      botaoEnviar.setOutputMarkupPlaceholderTag(true);
      formLogin.setOutputMarkupPlaceholderTag(true);
      formLogin.add(botaoEnviar);
      add(formLogin);
   }

   private void setDefaultResponsePageIfNecessary()
   {
      setResponsePage(getApplication().getHomePage());
   }
}
