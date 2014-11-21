package com.br.GrandeViaFitness.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.MensalidadeDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Mensalidade;

@Repository
public class MensalidadeDaoImpl extends JpaDao<Mensalidade> implements MensalidadeDao
{
   private static final long serialVersionUID = 2998351516054590553L;

   @Override
   public Integer buscaMensalidade(final Mensalidade mensalidade)
   {
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(m.codigo) FROM Mensalidade m ");
      if (mensalidade != null)
      {
         montaConsultaGenerica(sb, params, mensalidade);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final Mensalidade mensalidade)
   {
      sb.append(" WHERE 1 = 1");
      if (mensalidade.getPessoa() != null)
      {
         if (mensalidade.getPessoa().getNomePessoa() != null)
         {
            sb.append(" AND  UPPER(m.pessoa.nomePessoa) LIKE :pessoaNome ");
            params.put("pessoaNome", "%" + mensalidade.getPessoa().getNomePessoa().toUpperCase() + "%");
         }
      }
      if (mensalidade.getDataFim() != null)
      {
         sb.append(" AND m.dataPagamento >= :datainicio and m.dataPagamento <= :dataFim ");
         params.put("datainicio", mensalidade.getDataPagamento());
         params.put("dataFim", mensalidade.getDataFim());
      }
      else
      {
         if (mensalidade.getDataPagamento() != null)
         {
            sb.append(" AND m.dataPagamento = :data ");
            params.put("data", mensalidade.getDataPagamento());
         }
      }
      if (mensalidade.getValorPago() != null)
      {
         sb.append(" AND m.valorPago = :valor ");
         params.put("valor", mensalidade.getValorPago());
      }
      if (mensalidade.getMesReferente() != null)
      {
         sb.append(" AND m.mesReferente = :mes ");
         params.put("mes", mensalidade.getMesReferente());
      }
      if (mensalidade.getAnoReferencia() != null)
      {
         sb.append(" AND m.anoReferencia = :ano ");
         params.put("ano", mensalidade.getAnoReferencia());
      }

   }

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      final Mensalidade auxFiltro = (Mensalidade) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(m.codigo) FROM Mensalidade m ");
      if (auxFiltro != null)
      {
         montaConsultaGenerica(sb, params, auxFiltro);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   @Override
   public List<Mensalidade> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      final Mensalidade auxFiltro = (Mensalidade) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT m FROM Mensalidade m ");
      sb.append(" JOIN FETCH m.pessoa pessoa ");
      if (auxFiltro != null)
      {
         montaConsultaGenerica(sb, params, auxFiltro);
      }

      if (ordernar != null)
      {
         if (ordernar.getColuna().equals("dataPagamentoFormatada"))
         {
            sb.append(" ORDER BY m.dataPagamento " + ordernar.getOrdernar());
         }
         else if (ordernar.getColuna().equals("valorPagoFormatado"))
         {
            sb.append(" ORDER BY m.valorPago " + ordernar.getOrdernar());
         }
         else if (ordernar.getColuna().equals("mesReferente.descricao"))
         {
            sb.append(" ORDER BY m.mesReferente " + ordernar.getOrdernar());
         }
         else
         {
            sb.append(" ORDER BY m." + ordernar.getColuna() + " " + ordernar.getOrdernar());
         }
      }
      return consulta(sb.toString(), params);
   }

   @Override
   public String calculaSaldo(final Mensalidade filtro)
   {
      final Mensalidade auxFiltro = filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT m FROM Mensalidade m ");
      sb.append(" JOIN FETCH m.pessoa pessoa ");
      if (auxFiltro != null)
      {
         montaConsultaGenerica(sb, params, auxFiltro);
      }
      BigDecimal valorAtual = new BigDecimal(0);
      for (final Mensalidade auxmeMensalidade : consulta(sb.toString(), params))
      {
         valorAtual = valorAtual.add(auxmeMensalidade.getValorPago());
      }
      valorAtual.setScale(2, RoundingMode.HALF_UP);
      final DecimalFormat df = new DecimalFormat(",##0.00");

      return df.format(valorAtual);
   }

}
