package com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.enumUtil.Mensagem;
import com.br.GrandeViaFitness.enumUtil.UtilRadioEnum;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.exercicio.consultar.ConsultarExercicioIndex;

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
   private DropDownChoice<TipoEquipamento> comboEquipamento;
   private DropDownChoice<Corpo> comboCorpo;
   private FeedbackPanel feedBack;
   @SpringBean
   private TipoExercicioAS tipoExercicioAS;
   private RadioGroup<UtilRadioEnum> radioGroupEquipamento;

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
      criaComboBox();
      criaRadio();
      criaFeedBack();

   }

   private void criaFeedBack()
   {
      feedBack = new FeedbackPanel("feedBack");
      feedBack.setOutputMarkupId(true);

      addOrReplace(feedBack);
   }

   private void criaComboBox()
   {


      final List<Corpo> listaCorpo = new ArrayList<Corpo>();
      comboEquipamento =
         new DropDownChoice<TipoEquipamento>("comboEquipamento", new Model<TipoEquipamento>(), getListaEquipamento(),
            new ChoiceRenderer<TipoEquipamento>("nomeTipoEquip", "codigo"))
         {
            private static final long serialVersionUID = 8205686180697927445L;

            @Override
            protected void onConfigure()
            {
               setChoices(getListaEquipamento());
            }
         };
      comboEquipamento.setOutputMarkupId(true);
      comboEquipamento.add(new AjaxFormComponentUpdatingBehavior("onChange")
      {
         private static final long serialVersionUID = -8555497402924464585L;

         @Override
         protected void onUpdate(final AjaxRequestTarget target)
         {
            tipoEquipamento = null;
            if (comboEquipamento.getModelObject() != null)
            {
               tipoEquipamento = comboEquipamento.getModelObject();
            }
            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento);

         }

         @Override
         protected void onError(final AjaxRequestTarget target, final RuntimeException e)
         {
            // TODO Auto-generated method stub
            super.onError(target, e);
         }

      });
      comboCorpo = new DropDownChoice<Corpo>("comboCorpo", new Model<Corpo>(), listaCorpo);
      comboCorpo.setOutputMarkupId(true);

      addOrReplace(comboEquipamento, comboCorpo);

   }

   private List<TipoEquipamento> getListaEquipamento()
   {
      return tipoExercicioAS.buscaListaEquipamento();
   }

   private void criaRadio()
   {

      radioGroupEquipamento = new RadioGroup<UtilRadioEnum>("radioEquipamento", new PropertyModel<UtilRadioEnum>(this, "opcaoEquipamento"));
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("alterarEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.ALTERAR),
         radioGroupEquipamento));
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("excluirEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.EXCLUIR),
         radioGroupEquipamento));
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("novoEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.NOVO),
         radioGroupEquipamento));
      addOrReplace(radioGroupEquipamento);
      radioGroupEquipamento.add(new AjaxFormComponentUpdatingBehavior("onchange")
      {
         private static final long serialVersionUID = 7229840565528776181L;

         @Override
         protected void onUpdate(final AjaxRequestTarget target)
         {
            if (opcaoEquipamento == UtilRadioEnum.NOVO)
            {

            }
            else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
            {

            }
            else
            {

            }

         }
      });
      radioGroupEquipamento.setRenderBodyOnly(false);
      radioGroupEquipamento.setOutputMarkupPlaceholderTag(true);
   }

   private void verificaOpcaoSelecionada()
   {
      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         tipoExercicioAS.persisteDadosEquipamento(tipoEquipamento.getClone());
         success(Mensagem.recuperaMensagem(Mensagem.M04));
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         tipoExercicioAS.persisteDadosEquipamento(tipoEquipamento.getClone());
         success(Mensagem.recuperaMensagem(Mensagem.M05));
      }
      else
      {

      }

   }

   private void criaBotoes()
   {
      add(new AjaxButton("btnSalvarEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCamposEquipamentos())
            {
               verificaOpcaoSelecionada();

               tipoEquipamento.setNomeTipoEquip(null);
               tipoEquipamento.setDescricaoTipoEquip(null);
               opcaoEquipamento = null;
            }
            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento, comboEquipamento, radioGroupEquipamento, feedBack);
         }
      });
      add(new AjaxButton("btnLimparEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            tipoEquipamento = new TipoEquipamento();
            setPropertyEquipamento();
            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento);
         }

         @Override
         protected void onError(final AjaxRequestTarget target, final Form<?> form)
         {
            atualizaTela(target, feedBack);
         }
      });
      add(new AjaxButton("btnSalvarMembro")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampoMembro())
            {
            }

            atualizaTela(target, feedBack);
         }
      });
      add(new AjaxButton("btnLimparMembro")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            corpo = new Corpo();
            setPropertyCorpo();
            atualizaTela(target, campoNomeCorpo, campoDescricaoCorpo);
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

   private boolean validaCampoMembro()
   {
      Boolean valido = true;
      if (campoNomeCorpo.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M03, campoNomeCorpo.getLabel().getObject()));
         valido = false;
      }
      if (campoDescricaoCorpo.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M03, campoDescricaoCorpo.getLabel().getObject()));
         valido = false;
      }
      return valido;
   }

   private boolean validaCamposEquipamentos()
   {
      Boolean valido = true;
      if (campoNomeEquipamento.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M03, campoNomeEquipamento.getLabel().getObject()));
         valido = false;
      }
      if (campoDescricaoEquipamento.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M03, campoDescricaoEquipamento.getLabel().getObject()));
         valido = false;
      }

      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         if (tipoExercicio.getCodigo() != null)
         {
            error("É necessarioa limpar campos do Equipamento para cadastrar um novo.");
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         if (tipoExercicio.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M08));
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.EXCLUIR)
      {
         if (tipoExercicio.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M07));
            valido = false;
         }
      }
      else
      {
         error(Mensagem.recuperaMensagem(Mensagem.M06));
         valido = false;
      }
      return valido;
   }

   private void criaCampos()
   {
      campoNomeExercicio = new TextField<String>("nomeExercicio");
      campoDescricaoExercicio = new TextArea<String>("descricaoExercicio");

      campoDescricaoEquipamento =
         new TextArea<String>("descricaoTipoEquip", new PropertyModel<String>(tipoEquipamento, "descricaoTipoEquip"));
      campoDescricaoEquipamento.setLabel(new Model<String>("descrição do equipamento"));

      campoNomeEquipamento = new TextField<String>("nomeTipoEquip", new PropertyModel<String>(tipoEquipamento, "nomeTipoEquip"));
      campoNomeEquipamento.setLabel(new Model<String>("nome do equipamento"));

      campoNomeCorpo = new TextField<String>("nomeMembroCorpo", new PropertyModel<String>(corpo, "nomeMembroCorpo"));
      campoNomeCorpo.setLabel(new Model<String>("nome do membro"));

      campoDescricaoCorpo = new TextArea<String>("descricaoMembroCorpo", new PropertyModel<String>(corpo, "descricaoMembroCorpo"));
      campoDescricaoCorpo.setLabel(new Model<String>("descrição do membro"));

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
