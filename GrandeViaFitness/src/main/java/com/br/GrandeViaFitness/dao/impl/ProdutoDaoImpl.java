package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.ProdutoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.utilitario.Paginacao;

@Repository
public class ProdutoDaoImpl extends JpaDao<Produto> implements ProdutoDao
{
   private static final long serialVersionUID = 1176117668980666439L;

   @Override
   public List<Produto> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      final Produto filtroProduto = (Produto) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT p FROM Produto p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtroProduto);
      }
      if (ordernar != null)
      {
         sb.append("ORDER BY p." + ordernar.getColuna() + " " + ordernar.getOrdernar());
      }
      return findByNamedParams(sb.toString(), params, new Paginacao(first, count));
   }

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      final Produto filtroProduto = (Produto) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(p.codigo) FROM Produto p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtroProduto);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final Produto filtroProduto)
   {
      sb.append(" WHERE 1 = 1 ");
      if (filtroProduto.getCodigo() != null)
      {
         sb.append(" AND p.codigo = :codigo ");
         params.put("codigo", filtroProduto.getCodigo());
      }
      if (filtroProduto.getNomeProduto() != null)
      {
         sb.append(" AND p.nomeProduto = :nome ");
         params.put("nome", filtroProduto.getNomeProduto());
      }
   }
}
