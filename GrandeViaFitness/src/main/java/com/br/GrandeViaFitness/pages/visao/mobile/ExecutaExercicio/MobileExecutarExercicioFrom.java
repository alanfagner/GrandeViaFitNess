package com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.RlPessoaExercicioAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileVisualizarExercicio.MobileVisualizarExercicioIndex;

public class MobileExecutarExercicioFrom extends FormularioBase<RlPessoaExercicio>
{

   private static final long serialVersionUID = 303054331978593054L;
   private DataGridGenerica<RlPessoaExercicio, String> gridGenerica;
   private ProviderGenerico<RlPessoaExercicio, String> providerGenerico;
   @SpringBean
   private RlPessoaExercicioAS rlPessoaExercicioAS;
   private final RlPessoaExercicio rlPessoaExercicio;

   public MobileExecutarExercicioFrom(final String id, final RlPessoaExercicio pessoaExercico)
   {
      super(id, new CompoundPropertyModel<RlPessoaExercicio>(pessoaExercico));
      rlPessoaExercicio = pessoaExercico;
      inicializar();
   }

   private void inicializar()
   {
      criaGrid();
      criaBotoes();

   }

   private void criaBotoes()
   {
      addOrReplace(new AjaxButton("btnVoltar")
      {
         private static final long serialVersionUID = 7044710643044229617L;

         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new MobileVisualizarExercicioIndex(rlPessoaExercicio.getTipoExercicio()));
         }
      });

   }

   private void criaGrid()
   {

      add(new GridView<RlPessoaExercicio>("rows", getProviderGenerico())
      {
         private static final long serialVersionUID = 5797362614514680990L;

         @Override
         protected void populateItem(final Item<RlPessoaExercicio> item)
         {
            final RlPessoaExercicio rlPessoaExercicio = item.getModelObject();
            item.add(new Label("firstName", rlPessoaExercicio.getDataExercicio()));

         }

         @Override
         protected void populateEmptyItem(final Item<RlPessoaExercicio> item)
         {
            // TODO Auto-generated method stub

         }
      });
      /*
            final List<IColumn<RlPessoaExercicio, String>> columns = new ArrayList<IColumn<RlPessoaExercicio, String>>();
            final List<AjaxLink<RlPessoaExercicio>> listaBotoes = new ArrayList<AjaxLink<RlPessoaExercicio>>();
            columns.add(DataGridGenerica.criaColunarRlPessoaExercicio("Codigo", "codigo", true, 5));
            columns.add(DataGridGenerica.criaColunarRlPessoaExercicio("Número Repetições", "numeroRepeticoes", true, 40));
            columns.add(DataGridGenerica.criaColunarRlPessoaExercicio("Quantidade de Peso", "quatidadePeso", true, 10));
            columns.add(DataGridGenerica.criaColunarRlPessoaExercicio("Número de Series", "numeroSeries", true, 40));
            columns.add(new AbstractColumn<RlPessoaExercicio, String>(new Model<String>("Opções"))
            {
               private static final long serialVersionUID = -3102670641136395641L;


               @Override
               public String getCssClass()
               {
                  return "tam5";
               }

               @Override
               public void populateItem(final Item<ICellPopulator<RlPessoaExercicio>> cellItem, final String componentId,
                  final IModel<RlPessoaExercicio> entidade)
               {
                  if (listaBotoes.size() > 0)
                  {
                     cellItem.add(new ActionButtonPanel<RlPessoaExercicio>(componentId, entidade, listaBotoes));
                  }

               }

            });
            gridGenerica = new DataGridGenerica<RlPessoaExercicio, String>("table", columns, getProviderGenerico(), 5)
            {
               private static final long serialVersionUID = -2837712007974126400L;

            };
            gridGenerica.setOutputMarkupPlaceholderTag(true);
            addOrReplace(gridGenerica);*/
   }

   public ProviderGenerico<RlPessoaExercicio, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         providerGenerico = new ProviderGenerico<RlPessoaExercicio, String>(rlPessoaExercicioAS, getModelObject());
         providerGenerico.setOrdernar(new ParametrosOrdenacao("dataExercicio", true));
      }
      return providerGenerico;
   }

}
