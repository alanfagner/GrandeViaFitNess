package com.br.GrandeViaFitness.componentes;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.googlecode.wicket.jquery.ui.JQueryIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.AbstractDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.AbstractFormDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButtons;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.MessageFormDialog;

public abstract class ConfirmAjaxButtonCustom<T> extends GenericPanel<String>
{
   private static final long serialVersionUID = 1L;
   public final AjaxLink<T> button;
   private T entidade;

   public T getEntidade()
   {
      return entidade;
   }

   public void setEntidade(final T entidade)
   {
      this.entidade = entidade;
   }

   /** Constructor
    *
    * @param id markup id
    * @param label the button text
    * @param title the dialog title
    * @param message the dialog message */
   public ConfirmAjaxButtonCustom(final String id, final String label, final String title, final String message)
   {
      this(id, Model.of(label), Model.of(title), Model.of(message));
   }

   /** Constructor
    * @param <T>
    *
    * @param id markup id
    * @param label the button text
    * @param title the dialog title
    * @param message the dialog message */
   public ConfirmAjaxButtonCustom(final String id, final IModel<String> label, final IModel<String> title, final IModel<String> message)
   {
      super(id, message);

      final AbstractFormDialog<?> dialog = this.newFormDialog("dialog", title, this.getModel());
      this.add(dialog);

      button = new AjaxLink<T>("button")
      {
         private static final long serialVersionUID = -2400707033647917551L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            dialog.open(target);

         }
      };
      button.setOutputMarkupPlaceholderTag(true);
      button.setVisibilityAllowed(false);

      this.add(button); // does not validate the form before the dialog is being displayed

      button.add(new Label("label", label).setRenderBodyOnly(true));
   }

   // Properties //
   /** Gets the icon being displayed in the button
    *
    * @return the {@link JQueryIcon} */
   protected String getIcon()
   {
      return JQueryIcon.ALERT;
   }

   // Events //

   /** Triggered when the form is submitted, but the validation failed
    *
    * @param target the {@link AjaxRequestTarget}
    * @param form the {@link Form} */
   protected abstract void onError(AjaxRequestTarget target, Form<?> form);

   /** Triggered when the form is submitted, and the validation succeed
    *
    * @param target the {@link AjaxRequestTarget}
    * @param form the {@link Form} */
   protected abstract void onSubmit(AjaxRequestTarget target, Form<?> form);

   // Factories //

   /** Create the dialog instance<br/>
    * <b>Warning:</b> to be overridden with care!
    *
    * @param id the markupId
    * @param title the title of the dialog
    * @param message the message to be displayed
    * @return the dialog instance */
   protected AbstractFormDialog<?> newFormDialog(final String id, final IModel<String> title, final IModel<String> message)
   {
      return new MessageFormDialog(id, title, message, DialogButtons.OK_CANCEL, DialogIcon.WARN)
      {

         private static final long serialVersionUID = 1L;

         @Override
         protected DialogButton getSubmitButton()
         {
            return this.findButton(AbstractDialog.LBL_OK);
         }

         @Override
         public Form<?> getForm()
         {
            return Form.findForm(ConfirmAjaxButtonCustom.this);
         }

         @Override
         protected void onError(final AjaxRequestTarget target)
         {
            super.close(target, null); // the dialog does not close on error, by default.

            ConfirmAjaxButtonCustom.this.onError(target, this.getForm());
         }

         @Override
         protected void onSubmit(final AjaxRequestTarget target)
         {
            ConfirmAjaxButtonCustom.this.onSubmit(target, this.getForm());
         }
      };
   }
}
