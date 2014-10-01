package com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.enumUtil.UtilRadioEnum;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.exercicio.consultar.ConsultarExercicioIndex;
import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;

public class CadastrarAlterarExercicioForm extends FormularioBase<TipoExercicio>
{
   private static final long serialVersionUID = 8617765087518222595L;
   private final Boolean isAlterar;
   private final TipoExercicio tipoExercicio;
   private TipoEquipamento tipoEquipamento;
   private Corpo corpo;
   private TextArea<String> campoDescricaoExercicio;
   private TextField<String> campoNomeExercicio;
   private TextArea<String> campoDescricaoEquipamento;
   private TextField<String> campoNomeEquipamento;
   private TextField<String> campoNomeCorpo;
   private TextArea<String> campoDescricaoCorpo;
   private UtilRadioEnum opcaoEquipamento;
   private UtilRadioEnum opcaoCorpo;

   public CadastrarAlterarExercicioForm(final String id, final Boolean isAlterar, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      this.tipoExercicio = tipoExercicio;
      this.isAlterar = isAlterar;
      inicializar();
   }

   private void inicializar()
   {
      tipoEquipamento = new TipoEquipamento();
      corpo = new Corpo();
      crialabel();
      criaCampos();
      criaBotoes();
      criaRadio();

   }

   private void criaRadio()
   {
      final RadioGroup<UtilRadioEnum> RadioGroupEquipamento =
         new RadioGroup<UtilRadioEnum>("radioEquipamento", new PropertyModel<UtilRadioEnum>(this, "opcaoEquipamento"));
      RadioGroupEquipamento.add(new Radio<UtilRadioEnum>("alterarEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.ALTERAR)));
      RadioGroupEquipamento.add(new Radio<UtilRadioEnum>("excluirEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.EXCLUIR)));
      RadioGroupEquipamento.add(new Radio<UtilRadioEnum>("novoEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.NOVO)));
      addOrReplace(RadioGroupEquipamento);

      final RadioGroup<UtilRadioEnum> RadioGroupCropo =
         new RadioGroup<UtilRadioEnum>("radioCorpo", new PropertyModel<UtilRadioEnum>(this, "opcaoCorpo"));
      RadioGroupCropo.add(new Radio<UtilRadioEnum>("alterarCorpo", new Model<UtilRadioEnum>(UtilRadioEnum.ALTERAR)));
      RadioGroupCropo.add(new Radio<UtilRadioEnum>("excluirCorpo", new Model<UtilRadioEnum>(UtilRadioEnum.EXCLUIR)));
      RadioGroupCropo.add(new Radio<UtilRadioEnum>("novoCorpo", new Model<UtilRadioEnum>(UtilRadioEnum.NOVO)));
      addOrReplace(RadioGroupCropo);
   }

   private void criaBotoes()
   {
      add(new AjaxButton("btnSalvarEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;
      });
      add(new AjaxButton("btnLimparEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            tipoEquipamento = new TipoEquipamento();
            setPropertyEquipamento();
            atualizaTela(target);
            target.add(campoNomeEquipamento, campoDescricaoEquipamento);
         }
      });
      add(new AjaxButton("btnSalvarMembro")
      {
         private static final long serialVersionUID = 185258892178782834L;
      });
      add(new AjaxButton("btnLimparMembro")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            corpo = new Corpo();
            setPropertyCorpo();
            atualizaTela(target);
            target.add(campoNomeCorpo, campoDescricaoCorpo);
         }
      });
      add(new AjaxButton("btnSalvar")
      {
         private static final long serialVersionUID = 185258892178782834L;
      });

      add(new AjaxButton("btnVoltar")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new ConsultarExercicioIndex());
         }

         @Override
         protected void onConfigure()
         {
            setDefaultFormProcessing(false);
         }
      });

   }

   private void criaCampos()
   {
      campoNomeExercicio = new TextField<String>("nomeExercicio");
      campoDescricaoExercicio = new TextArea<String>("descricaoExercicio");
      campoNomeEquipamento = new TextField<String>("nomeTipoEquip", new PropertyModel<String>(tipoEquipamento, "nomeTipoEquip"));
      campoDescricaoEquipamento =
         new TextArea<String>("descricaoTipoEquip", new PropertyModel<String>(tipoEquipamento, "descricaoTipoEquip"));
      campoNomeCorpo = new TextField<String>("nomeMembroCorpo", new PropertyModel<String>(corpo, "nomeMembroCorpo"));
      campoDescricaoCorpo = new TextArea<String>("descricaoMembroCorpo", new PropertyModel<String>(corpo, "descricaoMembroCorpo"));

      campoNomeExercicio.setOutputMarkupId(true);
      campoDescricaoExercicio.setOutputMarkupId(true);
      campoNomeEquipamento.setOutputMarkupId(true);
      campoDescricaoEquipamento.setOutputMarkupId(true);
      campoNomeCorpo.setOutputMarkupId(true);
      campoDescricaoCorpo.setOutputMarkupId(true);

      addOrReplace(campoNomeExercicio, campoDescricaoExercicio, campoNomeEquipamento, campoDescricaoEquipamento, campoNomeCorpo,
         campoDescricaoCorpo);

   }

   private void setPropertyCorpo()
   {
      campoNomeCorpo.setModel(new PropertyModel<String>(corpo, "nomeMembroCorpo"));
      campoDescricaoCorpo.setModel(new PropertyModel<String>(corpo, "descricaoMembroCorpo"));
   }

   private void setPropertyEquipamento()
   {
      campoNomeEquipamento.setModel(new PropertyModel<String>(tipoEquipamento, "nomeTipoEquip"));
      campoDescricaoEquipamento.setModel(new PropertyModel<String>(tipoEquipamento, "descricaoTipoEquip"));
   }

   private void crialabel()
   {
      if (isAlterar)
      {
         addOrReplace(new Label("Label1", "Alterar Exercício"));
      }
      else
      {
         addOrReplace(new Label("Label1", "Cadastrar Novo Exercício"));
      }

   }

}
