package com.br.GrandeViaFitness.componentes;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.model.Pessoa;

public class FormularioBase<T> extends Form<T>
{
   private static final long serialVersionUID = -5262964465965255989L;
   private Pessoa usuarioLogado;
   private static String pathName;
   @SpringBean
   private PessoaAS pessoaAS;

   public FormularioBase(final String id, final IModel<T> model)
   {
      super(id, model);
      setOutputMarkupId(true);
      FormularioBase.setPathName("c:\\pictures");
   }

   public FormularioBase(final String id)
   {
      super(id);
   }

   public void atualizaTela(final AjaxRequestTarget target, final Component... component)
   {
      target.appendJavaScript("addScriptCss();");
      for (final Component componente : component)
      {
         if (componente.getClass().getSimpleName().equals("FeedbackPanel"))
         {
            // target.appendJavaScript("setTimeout(function(){$('#" + componente.getMarkupId() + "').hide('slow')}, 8000);");
         }
      }
      target.add(component);
   }

   public static String getPathName()
   {
      return FormularioBase.pathName;
   }

   public static void setPathName(final String pathName)
   {
      FormularioBase.pathName = pathName;
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
