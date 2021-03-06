package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.RLProdutoVendaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.model.RlProdutoVenda;

@Repository
public class RLProdutoVendaDaoImpl extends JpaDao<RlProdutoVenda> implements RLProdutoVendaDao
{
   private static final long serialVersionUID = 5364397220727102343L;

   @Override
   public List<RlProdutoVenda> buscaListaGrid(final Entidade entidade, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      final RlProdutoVenda filtroProdutoVenda = (RlProdutoVenda) entidade;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT pv FROM RlProdutoVenda pv ");
      sb.append(" JOIN FETCH pv.produto p ");
      if (filtroProdutoVenda != null)
      {
         montaConsultaGenerica(sb, params, filtroProdutoVenda);
      }
      return consulta(sb.toString(), params);
   }

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      final RlProdutoVenda filtroProdutoVenda = (RlProdutoVenda) entidade;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(pv.codigo) FROM RlProdutoVenda pv ");
      if (filtroProdutoVenda != null)
      {
         montaConsultaGenerica(sb, params, filtroProdutoVenda);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final RlProdutoVenda filtroProdutoVenda)
   {
      sb.append(" WHERE 1 = 1");
      if (filtroProdutoVenda.getProduto() != null)
      {
         if (filtroProdutoVenda.getProduto().getNomeProduto() != null)
         {
            sb.append(" AND pv.produto = :produto");
            params.put("produto", filtroProdutoVenda.getProduto());
         }
      }

      if (filtroProdutoVenda.getVenda() != null)
      {
         sb.append(" AND pv.venda = :venda");
         params.put("venda", filtroProdutoVenda.getVenda());
      }
   }

   @Override
   public List<RlProdutoVenda> buscaListaProdutoVendaPorCodigoVenda(final Long codigo)
   {
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT r FROM RlProdutoVenda r ");
      sb.append(" WHERE r.venda.codigo = :codigo ");
      params.put("codigo", codigo);

      return consulta(sb.toString(), params);
   }

   @Override
   public List<RlProdutoVenda> buscaListaPorProduto(final Produto produto)
   {
      final RlProdutoVenda filtroProdutoVenda = new RlProdutoVenda();
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT pv FROM RlProdutoVenda pv ");
      sb.append(" JOIN FETCH pv.produto p ");
      if (filtroProdutoVenda != null)
      {
         montaConsultaGenerica(sb, params, filtroProdutoVenda);
      }
      return consulta(sb.toString(), params);
   }
}
