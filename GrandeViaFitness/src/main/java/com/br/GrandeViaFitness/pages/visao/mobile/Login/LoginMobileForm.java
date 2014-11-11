package com.br.GrandeViaFitness.pages.visao.mobile.Login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.User;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class LoginMobileForm extends FormularioBase<User>
{
   private static final long serialVersionUID = -1099972925267877402L;

   private AjaxButtonCustom botaoEnviar;
   private User auxUser;
   private FeedBackPanelCustom feedBack;
   private AjaxButtonCustom botaoLimpar;

   public LoginMobileForm(final String id)
   {
      super(id);
      iniciliar();
   }

   private void iniciliar()
   {
      auxUser = new User();
      add(new TextField<String>("cpf", new PropertyModel<String>(auxUser, "cpf")));
      add(new PasswordTextField("password", new PropertyModel<String>(auxUser, "password")));
      botaoEnviar = new AjaxButtonCustom("btnEnviar")
      {
         private static final long serialVersionUID = 5503520007522761497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            final AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(Util.retirarMascara(auxUser.getCpf()), auxUser.getPassword()))
            {
               setDefaultResponsePageIfNecessary();
            }
            else
            {
               error("Usuario/Senha invalida.");
               atualizaTela(target, feedBack);

            }
            form.getModelObject();
         }
      };
      botaoEnviar.setOutputMarkupPlaceholderTag(true);

      botaoLimpar = new AjaxButtonCustom("btnLimpar")
      {
         private static final long serialVersionUID = 5503520007522761497L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new LoginMobileIndex());
         }
      };
      botaoLimpar.setDefaultFormProcessing(false);
      addOrReplace(botaoEnviar, botaoLimpar);
      auxUser.setCpf("34520184827");
      auxUser.setPassword("3451985");

      criaFeedBack();
   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupId(true);
      addOrReplace(feedBack);

   }

   private void setDefaultResponsePageIfNecessary()
   {
      setResponsePage(new MobileHomeIndex());
   }

}
