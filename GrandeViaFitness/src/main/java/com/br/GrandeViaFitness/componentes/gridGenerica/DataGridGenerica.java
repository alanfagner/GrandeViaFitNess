package com.br.GrandeViaFitness.componentes.gridGenerica;

import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.br.GrandeViaFitness.model.Mensalidade;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.model.Venda;

public class DataGridGenerica<T, S> extends DataTable<T, S>
{

   public DataGridGenerica(final String id, final List<? extends IColumn<T, S>> columns, final ISortableDataProvider<T, S> dataProvider,
      final long rowsPerPage)
   {
      super(id, columns, dataProvider, rowsPerPage);

      addTopToolbar(new NoRecordsToolbar(this));
      addBottomToolbar(new CustomNavigator<T, S>(this));
      addTopToolbar(new HeadersToolbar<S>(this, dataProvider));
   }

   public static PropertyColumn<RlProdutoVenda, String> criaColunarRlProdutoVenda(final String text, final String nomeModel,
      final Boolean orderncao, final Integer tamanho)
   {
      final PropertyColumn<RlProdutoVenda, String> coluna =
         new PropertyColumn<RlProdutoVenda, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }

   public static PropertyColumn<Venda, String> criaColunarVenda(final String text, final String nomeModel, final Boolean orderncao,
      final Integer tamanho)
   {
      final PropertyColumn<Venda, String> coluna =
         new PropertyColumn<Venda, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }

   public static PropertyColumn<Mensalidade, String> criaColunarMensalidade(final String text, final String nomeModel,
      final Boolean orderncao, final Integer tamanho)
   {
      final PropertyColumn<Mensalidade, String> coluna =
         new PropertyColumn<Mensalidade, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }

   public static PropertyColumn<RlPessoaExercicio, String> criaColunarRlPessoaExercicio(final String text, final String nomeModel,
      final Boolean orderncao,
      final Integer tamanho)
   {
      final PropertyColumn<RlPessoaExercicio, String> coluna =
         new PropertyColumn<RlPessoaExercicio, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }

   public static PropertyColumn<Produto, String> criaColunarProduto(final String text, final String nomeModel, final Boolean orderncao,
      final Integer tamanho)
   {
      final PropertyColumn<Produto, String> coluna =
         new PropertyColumn<Produto, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }


   public static PropertyColumn<Pessoa, String> criaColunarPessoa(final String text, final String nomeModel, final Boolean orderncao,
      final Integer tamanho)
   {
      final PropertyColumn<Pessoa, String> coluna =
         new PropertyColumn<Pessoa, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }

   public static PropertyColumn<TipoExercicio, String> criaColunarTipoExercico(final String text, final String nomeModel,
      final Boolean orderncao,
      final Integer tamanho)
   {
      final PropertyColumn<TipoExercicio, String> coluna =
         new PropertyColumn<TipoExercicio, String>(new Model<String>(text), nomeModel, orderncao ? nomeModel : null)
         {
            private static final long serialVersionUID = -8096001661154391568L;

            @Override
            public String getCssClass()
            {
               return "tam" + tamanho;
            }
         };
      coluna.getCssClass();
      return coluna;
   }
   @Override
   protected Item<T> newRowItem(final String id, final int index, final IModel<T> model)
   {
      return new OddEvenItem<T>(id, index, model);
   }

   private static final long serialVersionUID = -1418047019008118408L;

}
