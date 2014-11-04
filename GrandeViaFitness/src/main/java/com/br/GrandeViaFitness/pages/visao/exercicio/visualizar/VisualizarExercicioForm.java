package com.br.GrandeViaFitness.pages.visao.exercicio.visualizar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.AjaxButtonCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar.CadastrarAlterarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.exercicio.consultar.ConsultarExercicioIndex;

public class VisualizarExercicioForm extends FormularioBase<TipoExercicio>
{

   private static final long serialVersionUID = -8224166606149623890L;

   public VisualizarExercicioForm(final String id, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      inicializa();
   }

   private void inicializa()
   {
      crialabel();
      criaBotoes();
   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButtonCustom("alterar")
      {
         private static final long serialVersionUID = -5313175018763349124L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new CadastrarAlterarExercicioIndex(VisualizarExercicioForm.this.getModelObject()));
         }
      });

      addOrReplace(new AjaxButtonCustom("voltar")
      {
         private static final long serialVersionUID = 7895395494649143940L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new ConsultarExercicioIndex());
         }
      });

   }

   private void crialabel()
   {
      addOrReplace(new Label("nomeExercicio"));
      addOrReplace(new Label("descricaoExercicio"));
      addOrReplace(new Label("tipoEquipamento.nomeTipoEquip"));
      addOrReplace(new Label("tipoEquipamento.descricaoTipoEquip"));
      addOrReplace(new Label("corpo.nomeMembroCorpo"));
      addOrReplace(new Label("corpo.descricaoMembroCorpo"));

   }

}
