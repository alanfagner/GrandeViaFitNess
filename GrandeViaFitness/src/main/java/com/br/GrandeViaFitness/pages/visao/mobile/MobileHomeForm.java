package com.br.GrandeViaFitness.pages.visao.mobile;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;

public class MobileHomeForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = -3231330308077618602L;

   @SpringBean
   private CorpoAS corpoAS;
   private Date dataCadastro;

   private TextField<Date> campoData;

   public MobileHomeForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {

      criaBotoes();
      criaLabel();
      criaCampoData();
   }

   private void criaCampoData()
   {
      campoData = new TextField<Date>("campoData", new PropertyModel<Date>(this, "dataCadastro"))
      {
         private static final long serialVersionUID = 8493880801180408785L;

         @Override
         protected void onConfigure()
         {
            setOutputMarkupId(true);

         }
      };
      addOrReplace(campoData);
   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnLogado", getUsuarioLogado().getNomePessoa()));

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxSubmitLink("btnAvancar")
      {
         private static final long serialVersionUID = 1782016523465683542L;
         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new MobileMembroCorpoIndex(dataCadastro));
         }
      });

   }

}
