package com.br.GrandeViaFitness.pages.visao.mobile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;

public class MobileHomeForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = -3231330308077618602L;

   @SpringBean
   private CorpoAS corpoAS;

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
      // TODO Auto-generated method stub

   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnLogado", getUsuarioLogado().getNomePessoa()));

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxLink<TipoExercicio>("btnAvancar")
      {
         private static final long serialVersionUID = 1782016523465683542L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileMembroCorpoIndex());

         }
      });

   }

}
