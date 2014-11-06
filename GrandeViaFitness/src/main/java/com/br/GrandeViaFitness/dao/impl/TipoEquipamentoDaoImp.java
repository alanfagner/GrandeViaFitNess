package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.TipoEquipamentoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.TipoEquipamento;

@Repository
public class TipoEquipamentoDaoImp extends JpaDao<TipoEquipamento> implements TipoEquipamentoDao
{
   private static final long serialVersionUID = -1325360977814185046L;

   @Override
   public List<TipoEquipamento> buscaListaPorEquipamento(final TipoEquipamento auxTipoEqui)
   {

      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT te FROM TipoEquipamento te  ");
      if (auxTipoEqui != null)
      {
         montaConsultaGenerica(sb, params, auxTipoEqui);
      }
      return consulta(sb.toString(), params);
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final TipoEquipamento auxTipoEqui)
   {
      sb.append("WHERE 1 = 1");
      if (auxTipoEqui.getCodigo() != null)
      {
         sb.append(" AND te.codigo = :codigo ");
         params.put("codigo", auxTipoEqui.getCodigo());
      }
      if (auxTipoEqui.getNomeTipoEquip() != null)
      {
         sb.append(" AND UPPER(te.nomeTipoEquip) LIKE UPPER(:nome) ");
         params.put("nome", "%" + auxTipoEqui.getNomeTipoEquip() + "%");
      }
   }

}
