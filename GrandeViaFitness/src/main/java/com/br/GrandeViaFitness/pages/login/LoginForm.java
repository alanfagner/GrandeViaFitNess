package com.br.GrandeViaFitness.pages.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.User;

public class LoginForm extends FormularioBase<User>
{
   private static final long serialVersionUID = -6592036153332015004L;

   private final AjaxButton botaoEnviar;
   private final User auxUser;

   public LoginForm(final String id)
   {
      super(id);
      auxUser = new User();
      add(new TextField<String>("cpf", new PropertyModel<String>(auxUser, "cpf")));
      add(new TextField<String>("password", new PropertyModel<String>(auxUser, "password")));
      botaoEnviar = new AjaxButton("btnEnviar")
      {
         private static final long serialVersionUID = 5503520007522761497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            final AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(auxUser.getCpf(), auxUser.getPassword()))
            {
               getModelObject();
               setDefaultResponsePageIfNecessary();
            }
            else
            {
               error("Usuario/Senha invalida.");
               target.add(form);
               atualizaTela(target);
            }
            form.getModelObject();
         }
      };

      botaoEnviar.setOutputMarkupPlaceholderTag(true);
      addOrReplace(botaoEnviar, new Button("btnLimpar")
      {
         private static final long serialVersionUID = 5503520007522761497L;
         @Override
         public void onSubmit()
         {
            setResponsePage(new LoginIndex());
         }
      });
   }

   private void setDefaultResponsePageIfNecessary()
   {
      setResponsePage(getApplication().getHomePage());
   }

}
