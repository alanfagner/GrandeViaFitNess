package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.VendaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.utilitario.Paginacao;

@Repository
public class VendaDaoImpl extends JpaDao<Venda> implements VendaDao
{
   private static final long serialVersionUID = -730138955344653556L;

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      final Venda filtroVenda = (Venda) entidade;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(v.codigo) FROM Venda v ");

      if (filtroVenda != null)
      {
         montaConsultaGenerica(sb, params, filtroVenda);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final Venda filtroVenda)
   {
      sb.append(" WHERE 1 = 1 ");

      if (filtroVenda.getPessoa().getNomePessoa() != null)
      {
         sb.append(" AND v.pessoa.nomePessoa = :nome");
         params.put("nome", filtroVenda.getPessoa().getNomePessoa());
      }

      if (filtroVenda.getDataFim() != null)
      {
         sb.append(" AND v.dataVenda BETWEEN  :datainicio and  :dataFim ");
         params.put("datainicio", filtroVenda.getDataVenda());
         params.put("dataFim", filtroVenda.getDataFim());
      }
      else if (filtroVenda.getDataVenda() != null)
      {
         sb.append(" AND v.dataVenda = :data");
         params.put("data", filtroVenda.getDataVenda());
      }

   }

   @Override
   public List<Venda> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      final Venda filtroVenda = (Venda) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT v FROM Venda v ");
      sb.append(" JOIN FETCH v.pessoa p ");
      if (filtroVenda != null)
      {
         montaConsultaGenerica(sb, params, filtroVenda);
      }
      if (ordernar != null)
      {
         if (ordernar.getColuna().equals("dataFormatada"))
         {
            sb.append("ORDER BY v.dataVenda " + ordernar.getOrdernar());
         }
         else if (ordernar.getColuna().equals("valorFormatado"))
         {
            sb.append("ORDER BY v.valorTotal " + ordernar.getOrdernar());
         }
         else
         {
            sb.append("ORDER BY v." + ordernar.getColuna() + " " + ordernar.getOrdernar());
         }
      }
      return findByNamedParams(sb.toString(), params, new Paginacao(first, count));
   }
}
