package com.br.GrandeViaFitness.Componentes;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public class FormularioBase<T> extends Form<T>
{
   private static final long serialVersionUID = -5262964465965255989L;
   public FormularioBase(final String id, final IModel<T> model)
   {
      super(id, model);
   }

   public FormularioBase(final String id)
   {
      super(id);
   }

   @Override
   protected void onSubmit()
   {
   };

   public void atualizaTela(final AjaxRequestTarget target)
   {
      target.appendJavaScript("addScriptCss();");
   }
}
