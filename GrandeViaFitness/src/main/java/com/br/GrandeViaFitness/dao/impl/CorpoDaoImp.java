package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.CorpoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Corpo;

@Repository
public class CorpoDaoImp extends JpaDao<Corpo> implements CorpoDao
{
   private static final long serialVersionUID = -3508897487537738086L;

   @Override
   public List<Corpo> buscaListaPorCorpo(final Corpo corpo)
   {
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT co FROM Corpo co  ");
      if (corpo != null)
      {
         montaConsultaGenerica(sb, params, corpo);
      }
      return consulta(sb.toString(), params);
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final Corpo corpo)
   {
      sb.append("WHERE 1 = 1");
      if (corpo.getCodigo() != null)
      {
         sb.append(" AND co.codigo = :codigo ");
         params.put("codigo", corpo.getCodigo());
      }
      if (corpo.getNomeMembroCorpo() != null)
      {
         sb.append(" AND UPPER(co.nomeMembroCorpo) LIKE UPPER(:nome) ");
         params.put("nome", "%" + corpo.getNomeMembroCorpo() + "%");
      }

   }

}
