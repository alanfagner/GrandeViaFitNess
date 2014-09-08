package com.br.GrandeViaFitness.pages.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import com.br.GrandeViaFitness.Utilitario.Util;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.User;

public class LoginForm extends FormularioBase<User>
{
   private static final long serialVersionUID = -6592036153332015004L;

   private AjaxButton botaoEnviar;
   private User auxUser;
   private final FeedbackPanel feedBack;

   private Button botaoLimpar;

   public LoginForm(final String id, final FeedbackPanel feedBack)
   {
      super(id);
      this.feedBack = feedBack;
      inicializar();
   }

   private void inicializar()
   {

      auxUser = new User();
      add(new TextField<String>("cpf", new PropertyModel<String>(auxUser, "cpf")));
      add(new PasswordTextField("password", new PropertyModel<String>(auxUser, "password")));
      botaoEnviar = new AjaxButton("btnEnviar")
      {
         private static final long serialVersionUID = 5503520007522761497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            final AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(Util.retirarMascara(auxUser.getCpf()), auxUser.getPassword()))
            {
               getModelObject();
               setDefaultResponsePageIfNecessary();
            }
            else
            {
               error("Usuario/Senha invalida.");
               target.add(form, feedBack);
               atualizaTela(target);
            }
            form.getModelObject();
         }
      };
      botaoEnviar.setOutputMarkupPlaceholderTag(true);

      botaoLimpar = new Button("btnLimpar")
      {
         private static final long serialVersionUID = 5503520007522761497L;
         @Override
         public void onSubmit()
         {
            setResponsePage(new LoginIndex());
         }

         @Override
         public void onError()
         {
         }
      };
      botaoLimpar.setDefaultFormProcessing(false);
      addOrReplace(botaoEnviar, botaoLimpar);
   }

   private void setDefaultResponsePageIfNecessary()
   {
      setResponsePage(getApplication().getHomePage());
   }

}
