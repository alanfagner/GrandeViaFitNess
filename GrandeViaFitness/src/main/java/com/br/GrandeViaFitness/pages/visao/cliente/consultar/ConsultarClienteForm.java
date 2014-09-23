package com.br.GrandeViaFitness.pages.visao.cliente.consultar;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.BotoesGrid;
import com.br.GrandeViaFitness.componentes.gridGenerica.DataGridGenerica;
import com.br.GrandeViaFitness.componentes.provider.ProviderGenerico;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;
import com.br.GrandeViaFitness.pages.visao.cliente.cadastrar.CadastrarAlterarClienteIndex;

public class ConsultarClienteForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = 553958619270523962L;

   private DataGridGenerica<Pessoa, String> gridGenerica;
   private Pessoa filtro;
   private ProviderGenerico<Pessoa, String> providerGenerico;
   @SpringBean
   private PessoaAS pessoaAS;

   public ConsultarClienteForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      criaBotoes();
      criaGridCliente();
   }



   private void criaGridCliente()
     {
        final List<IColumn<Pessoa, String>> columns = new ArrayList<IColumn<Pessoa, String>>();
      final BotoesGrid<Pessoa> listBotoes = new BotoesGrid<Pessoa>();

        listBotoes.add(new AjaxLink<Pessoa>("Excluir")
        {
           private static final long serialVersionUID = -2007593370707695822L;

           @Override
           public void onClick(final AjaxRequestTarget target)
           {
            atualizaTela(target);
            target.add(gridGenerica);
           }

           @Override
           protected void onBeforeRender()
           {
              super.onBeforeRender();
           }
        });

        listBotoes.add(new AjaxLink<Pessoa>("Visualizar")
        {
           private static final long serialVersionUID = -2007593370707695822L;

           @Override
           public void onClick(final AjaxRequestTarget target)
           {
              getModelObject();
           }
        });
      columns.add(DataGridGenerica.criaColunar("Codigo", "codigo", true, 5));
      columns.add(DataGridGenerica.criaColunar("Nome", "nomePessoa", true, 40));
      columns.add(DataGridGenerica.criaColunar("CPF", "cpfPessoa", true, 10));
      columns.add(DataGridGenerica.criaColunar("Email", "emailPessoa", true, 40));

      columns.add((IColumn<Pessoa, String>) listBotoes.criaListaBotoes());
        gridGenerica =
 new DataGridGenerica<Pessoa, String>("table", columns, getProviderGenerico(), 5);
        gridGenerica.setOutputMarkupId(true);
        addOrReplace(gridGenerica);
   }

   private void criaBotoes()
   {
      final Button btnNovoCliente = new Button("btnNovoCliente")
      {
         private static final long serialVersionUID = -1540652083107892733L;

         @Override
         public void onSubmit()
         {
            setResponsePage(new CadastrarAlterarClienteIndex());
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
   public ProviderGenerico<Pessoa, String> getProviderGenerico()
   {
      if (providerGenerico == null)
      {
         filtro = new Pessoa();
         providerGenerico = new ProviderGenerico<Pessoa, String>(pessoaAS, filtro);
         providerGenerico.setOrdernar(new ParametrosOrdenacao("codigo", true));
      }
      return providerGenerico;
   }
}
