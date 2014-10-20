package com.br.GrandeViaFitness.pages.visao.exercicio.cadastrar;

import java.io.File;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.TipoExercicioAS;
import com.br.GrandeViaFitness.componentes.CriaImagenNonCachingImage;
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
   private FileUploadField foto1;
   private FileUploadField foto2;
   private Image imagen1;
   private NonCachingImage imagen2;

   public CadastrarAlterarExercicioForm(final String id, final Boolean isAlterar, final TipoExercicio tipoExercicio)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      this.tipoExercicio = tipoExercicio;
      this.isAlterar = isAlterar;
      setMultiPart(true);
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
      criaBotoesEditar();
      criaUploadFotos();
      criaFeedBack();
      criaImagen1(tipoExercicio.getFoto1(), tipoExercicio.getFoto2());
   }

   private void criaImagen1(final String path, final String path2)
   {

      imagen1 = CriaImagenNonCachingImage.criaImagen("myImage1", path);
      imagen1.setOutputMarkupPlaceholderTag(true);

      imagen2 = CriaImagenNonCachingImage.criaImagen("myImage2", path2);
      imagen2.setOutputMarkupPlaceholderTag(true);
      addOrReplace(imagen1, imagen2);

   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   private void criaUploadFotos()
   {

      foto1 = new FileUploadField("fileInput", new Model());
      foto2 = new FileUploadField("fileInput2", new Model());
      addOrReplace(foto1, foto2);

      addOrReplace(new AjaxButton("btnEnviarFotos")
      {
         private static final long serialVersionUID = 1919935682369201585L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {

            tipoExercicio.setFoto1(criaArquivo(foto1.getFileUpload()));
            tipoExercicio.setFoto2(criaArquivo(foto2.getFileUpload()));

            criaImagen1(tipoExercicio.getFoto1(), tipoExercicio.getFoto2());

            target.add(imagen1, imagen2);
         }
      });
   }

   private String criaArquivo(final FileUpload fileUpload)
   {
      final File newFile = new File(FormularioBase.getPathName() + "\\" + fileUpload.getClientFileName());
      if (newFile.exists())
      {
         newFile.delete();
      }

      try
      {
         newFile.createNewFile();
         fileUpload.writeTo(newFile);
      }
      catch (final Exception e)
      {
         throw new IllegalStateException("Error");
      }
      return fileUpload.getClientFileName();
   }

   private void criaBotoesEditar()
   {
      addOrReplace(new AjaxButton("btnEditarEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            tipoEquipamento = new TipoEquipamento();;
            if (comboEquipamento.getModelObject() != null)
            {
               tipoEquipamento = comboEquipamento.getModelObject();
            }
            setPropertyEquipamento();
            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento);
         }
      });

      addOrReplace(new AjaxButton("btnEditarMembro")
      {
         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }

         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            corpo = new Corpo();
            if (comboCorpo.getModelObject() != null)
            {
               corpo = comboCorpo.getModelObject();
            }
            setPropertyCorpo();
            atualizaTela(target, campoNomeCorpo, campoDescricaoCorpo);
         }
      });

   }

   private void criaFeedBack()
   {
      feedBack = new FeedbackPanel("feedBack");
      feedBack.setOutputMarkupId(true);

      addOrReplace(feedBack);
   }

   private void criaComboBox()
   {
      comboEquipamento =
         new DropDownChoice<TipoEquipamento>("comboEquipamento", new Model<TipoEquipamento>(
            tipoExercicio.getTipoEquipamento() != null ? tipoExercicio.getTipoEquipamento() : new TipoEquipamento()),
            getListaEquipamento(), new ChoiceRenderer<TipoEquipamento>("nomeTipoEquip", "codigo"))
         {
            private static final long serialVersionUID = 8205686180697927445L;

            @Override
            protected void onConfigure()
            {
               setChoices(getListaEquipamento());
            }
         };
      comboEquipamento.setOutputMarkupId(true);
      comboCorpo =
         new DropDownChoice<Corpo>("comboCorpo",
            new Model<Corpo>(tipoExercicio.getCorpo() != null ? tipoExercicio.getCorpo() : new Corpo()), getListaCorpo(),
            new ChoiceRenderer<Corpo>("nomeMembroCorpo", "codigo"))
         {
            private static final long serialVersionUID = 4653505046011709755L;

            @Override
            protected void onConfigure()
            {
               setChoices(getListaCorpo());
            }

         };
      comboCorpo.setOutputMarkupId(true);

      addOrReplace(comboEquipamento, comboCorpo);

   }

   private List<Corpo> getListaCorpo()
   {
      return tipoExercicioAS.buscaListaCorpo();
   }

   private List<TipoEquipamento> getListaEquipamento()
   {
      return tipoExercicioAS.buscaListaEquipamento();
   }

   private void criaRadio()
   {

      radioGroupEquipamento = new RadioGroup<UtilRadioEnum>("radioEquipamento", new PropertyModel<UtilRadioEnum>(this, "opcaoEquipamento"))
      {

         private static final long serialVersionUID = -4759959057527681222L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }
      };
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("alterarEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.ALTERAR),
         radioGroupEquipamento));
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("excluirEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.EXCLUIR),
         radioGroupEquipamento));
      radioGroupEquipamento.add(new Radio<UtilRadioEnum>("novoEquipamento", new Model<UtilRadioEnum>(UtilRadioEnum.NOVO),
         radioGroupEquipamento));
      addOrReplace(radioGroupEquipamento);
      radioGroupEquipamento.setRenderBodyOnly(false);
      radioGroupEquipamento.setOutputMarkupPlaceholderTag(true);
   }

   private void verificaOpcaoSelecionada()
   {
      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         tipoExercicioAS.persisteDadosEquipamento(tipoEquipamento.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M01, "equipamento"));
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         tipoExercicioAS.persisteDadosEquipamento(tipoEquipamento.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M03, "Equipamento"));
      }
      else
      {
         tipoExercicioAS.excluirEquipamento(tipoEquipamento.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M02, "Equipamento"));
      }

   }

   private void verificaOpcaoSelecionadaMembro()
   {
      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         tipoExercicioAS.persisteDadosCorpo(corpo.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M01, "Membro"));
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         tipoExercicioAS.persisteDadosCorpo(corpo.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M03, "Membro"));
      }
      else
      {
         tipoExercicioAS.excluirCorpo(corpo.getClone());
         getSession().success(Mensagem.recuperaMensagem(Mensagem.M02, "Membro"));
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
               setResponsePage(new CadastrarAlterarExercicioIndex());
            }
            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento, comboEquipamento, radioGroupEquipamento, feedBack);
         }

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }
      });
      add(new AjaxButton("btnLimparEquipamento")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            tipoEquipamento = new TipoEquipamento();
            opcaoEquipamento = null;
            comboEquipamento.setModelObject(null);
            setPropertyEquipamento();

            atualizaTela(target, campoNomeEquipamento, campoDescricaoEquipamento, comboEquipamento);
         }

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
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
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            if (validaCampoMembro())
            {
               verificaOpcaoSelecionadaMembro();
               setResponsePage(new CadastrarAlterarExercicioIndex());
            }

            atualizaTela(target, feedBack, campoNomeCorpo, campoDescricaoCorpo, comboCorpo);
         }
      });
      add(new AjaxButton("btnLimparMembro")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            corpo = new Corpo();
            setPropertyCorpo();
            opcaoEquipamento = null;
            comboCorpo.setModelObject(null);
            atualizaTela(target, campoNomeCorpo, campoDescricaoCorpo);
         }
      });
      add(new AjaxButton("btnSalvar")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            tipoEquipamento = comboEquipamento.getModelObject();
            corpo = comboCorpo.getModelObject();
            if (validaCamposExercicios())
            {
               tipoExercicio.setCorpo(corpo);
               tipoExercicio.setTipoEquipamento(tipoEquipamento);
               if (isAlterar)
               {
                  getSession().success(Mensagem.recuperaMensagem(Mensagem.M03, "Exercío"));
               }
               else
               {
                  getSession().success(Mensagem.recuperaMensagem(Mensagem.M01, "Exercío"));
               }
               tipoExercicioAS.persisteDadosExercicios(tipoExercicio);
               setResponsePage(new ConsultarExercicioIndex());
            }
            atualizaTela(target, feedBack);
         }

      });

      add(new Button("btnVoltar")
      {
         private static final long serialVersionUID = 185258892178782834L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new ConsultarExercicioIndex());
         }
      });

   }

   private boolean validaCamposExercicios()
   {
      Boolean valida = true;
      if (campoNomeExercicio.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoNomeExercicio.getLabel().getObject()));
         valida = false;
      }

      if (campoDescricaoExercicio.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoDescricaoExercicio.getLabel().getObject()));
         valida = false;
      }

      if (tipoEquipamento == null || tipoEquipamento.getCodigo() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "Equipamento"));
         valida = false;
      }

      if (corpo == null || corpo.getCodigo() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, "Corpo"));
         valida = false;
      }

      if (tipoExercicio.getFoto1() == null || tipoExercicio.getFoto2() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M10));
         valida = false;
      }

      return valida;
   }

   private boolean validaCampoMembro()
   {
      Boolean valido = true;
      if (campoNomeCorpo.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoNomeCorpo.getLabel().getObject()));
         valido = false;
      }
      if (campoDescricaoCorpo.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoDescricaoCorpo.getLabel().getObject()));
         valido = false;
      }

      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         if (corpo.getCodigo() != null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M08, "Membro"));
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         if (corpo.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M07, "Membro"));
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.EXCLUIR)
      {
         if (corpo.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M06, "Membro"));
            valido = false;
         }
      }
      else
      {
         error(Mensagem.recuperaMensagem(Mensagem.M05));
         valido = false;
      }
      return valido;

   }

   private boolean validaCamposEquipamentos()
   {
      Boolean valido = true;
      if (campoNomeEquipamento.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoNomeEquipamento.getLabel().getObject()));
         valido = false;
      }
      if (campoDescricaoEquipamento.getModelObject() == null)
      {
         error(Mensagem.recuperaMensagem(Mensagem.M04, campoDescricaoEquipamento.getLabel().getObject()));
         valido = false;
      }

      if (opcaoEquipamento == UtilRadioEnum.NOVO)
      {
         if (tipoEquipamento.getCodigo() != null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M08, "Equipamento"));
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.ALTERAR)
      {
         if (tipoEquipamento.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M07, "Equipamento"));
            valido = false;
         }
      }
      else if (opcaoEquipamento == UtilRadioEnum.EXCLUIR)
      {
         if (tipoEquipamento.getCodigo() == null)
         {
            error(Mensagem.recuperaMensagem(Mensagem.M06, "Equipamento"));
            valido = false;
         }
      }
      else
      {
         error(Mensagem.recuperaMensagem(Mensagem.M05));
         valido = false;
      }
      return valido;
   }

   private void criaCampos()
   {
      campoNomeExercicio = new TextField<String>("nomeExercicio", new PropertyModel<String>(tipoExercicio, "nomeExercicio"));
      campoNomeExercicio.setLabel(new Model<String>("nome exercício"));
      campoDescricaoExercicio = new TextArea<String>("descricaoExercicio", new PropertyModel<String>(tipoExercicio, "descricaoExercicio"));
      campoDescricaoExercicio.setLabel(new Model<String>("descrição exercício"));

      campoDescricaoEquipamento =
         new TextArea<String>("descricaoTipoEquip", new PropertyModel<String>(tipoEquipamento, "descricaoTipoEquip"))
         {
            /**
             *
             */
            private static final long serialVersionUID = 2776737015099178817L;

            @Override
            protected void onConfigure()
            {
               setEnabled(!isAlterar);
            }
         };
      campoDescricaoEquipamento.setLabel(new Model<String>("descrição do equipamento"));

      campoNomeEquipamento = new TextField<String>("nomeTipoEquip", new PropertyModel<String>(tipoEquipamento, "nomeTipoEquip"))
      {
         /**
          *
          */
         private static final long serialVersionUID = 1014558697437307429L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }
      };
      campoNomeEquipamento.setLabel(new Model<String>("nome do equipamento"));

      campoNomeCorpo = new TextField<String>("nomeMembroCorpo", new PropertyModel<String>(corpo, "nomeMembroCorpo"))
      {
         /**
          *
          */
         private static final long serialVersionUID = 3228424528656262434L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }
      };
      campoNomeCorpo.setLabel(new Model<String>("nome do membro"));

      campoDescricaoCorpo = new TextArea<String>("descricaoMembroCorpo", new PropertyModel<String>(corpo, "descricaoMembroCorpo"))
      {
         /**
          *
          */
         private static final long serialVersionUID = -4175784580279314655L;

         @Override
         protected void onConfigure()
         {
            setEnabled(!isAlterar);
         }
      };
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
