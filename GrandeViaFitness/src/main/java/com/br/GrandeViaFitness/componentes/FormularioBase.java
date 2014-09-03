package com.br.GrandeViaFitness.componentes;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

public class FormularioBase<T> extends Form<T>
{
   private static final long serialVersionUID = -5262964465965255989L;

   private FeedbackPanel feedback;
   public FormularioBase(final String id, final IModel<T> model)
   {
      super(id, model);
      criaFeedBack();
   }

   public FormularioBase(final String id)
   {
      super(id);
      criaFeedBack();
   }

   public void criaFeedBack()
   {
      feedback = new InformacaoAlerta("feedback");
      feedback.setFilter(new ContainerFeedbackMessageFilter(this));
      add(feedback);
   }

   public void atualizaTela(final AjaxRequestTarget target)
   {
      target.appendJavaScript("$.addScriptCss();");
   }

   public FeedbackPanel getFeedback()
   {
      return feedback;
   }

   public void setFeedback(final FeedbackPanel feedback)
   {
      this.feedback = feedback;
   };
}
