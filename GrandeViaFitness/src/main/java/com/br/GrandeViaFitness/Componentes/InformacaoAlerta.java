package com.br.GrandeViaFitness.Componentes;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class InformacaoAlerta extends FeedbackPanel
{
   public InformacaoAlerta(final String id)
   {
      super(id);
   }
   private static final long serialVersionUID = -5838499724468961972L;

   @Override
   protected String getCSSClass(final FeedbackMessage message)
   {
      return "msg" + message.getLevelAsString();
   }

   protected String getHint(final FeedbackMessage message)
   {
      String hint = "";
      if (message.getLevel() == FeedbackMessage.INFO || message.getLevel() == FeedbackMessage.SUCCESS)
      {
         hint = "Informações e Sucesso";
      }
      else if (message.getLevel() == FeedbackMessage.WARNING || message.getLevel() == FeedbackMessage.UNDEFINED)
      {
         hint = "Alerta";
      }
      else if (message.getLevel() == FeedbackMessage.ERROR || message.getLevel() == FeedbackMessage.FATAL)
      {
         hint = "Ocorreu um erro";
      }
      return hint;
   }
}
