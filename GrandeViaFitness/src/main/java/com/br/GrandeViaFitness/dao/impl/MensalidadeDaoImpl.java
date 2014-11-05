package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.MensalidadeDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
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
      if(mensalidade.getPessoa() != null){
         sb.append(" AND m.pessoa = :pessoa ");
         params.put("pessoa", mensalidade.getPessoa());
      }
      if (mensalidade.getDataPagamento() != null)
      {
         sb.append(" AND m.dataPagamento = :data ");
         params.put("data", mensalidade.getDataPagamento());
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

}
