package com.br.GrandeViaFitness.pages.visao.exercicio.consultar;

import org.apache.wicket.markup.html.form.Button;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar.CadastrarAlterarExercicioIndex;
import com.br.GrandeViaFitness.vo.AparelhosVO;

public class ConsultarExercicioForm extends FormularioBase<AparelhosVO>
{
   private static final long serialVersionUID = 2773647163866008579L;

   public ConsultarExercicioForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();

   }

   private void criaBotoes()
   {
      final Button btnNovoCliente = new Button("btnNovoExercicio")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new CadastrarAlterarExercicioIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };

      final Button btnVoltar = new Button("btnVoltar")
      {
         private static final long serialVersionUID = 7630777092486610559L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new HomePageIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      };
      addOrReplace(btnNovoCliente, btnVoltar);

   }

}
