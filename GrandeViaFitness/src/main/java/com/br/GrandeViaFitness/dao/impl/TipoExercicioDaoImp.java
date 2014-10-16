package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.TipoExercicioDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.utilitario.Paginacao;

@Repository
public class TipoExercicioDaoImp extends JpaDao<TipoExercicio> implements TipoExercicioDao
{
   private static final long serialVersionUID = 7613649066156038255L;

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      final TipoExercicio filtroTipoExercicio = (TipoExercicio) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(te.codigo) FROM TipoExercicio te ");
      if (filtroTipoExercicio != null)
      {
         montaConsultaGenerica(sb, params, filtroTipoExercicio);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final TipoExercicio filtro)
   {
      sb.append(" WHERE 1 = 1 ");
      if (filtro.getCodigo() != null)
      {
         sb.append(" AND te.codigo = :codigo ");
         params.put("codigo", filtro.getCodigo());
      }
      if (filtro.getNomeExercicio() != null)
      {
         sb.append(" AND UPPER(te.nomeExercicio) LIKE UPPER(:nome) ");
         params.put("nome", "%" + filtro.getNomeExercicio() + "%");
      }
      if (filtro.getCorpo() != null)
      {
         sb.append(" AND te.corpo = :corpo ");
         params.put("corpo", filtro.getCorpo());
      }
      if (filtro.getTipoEquipamento() != null)
      {
         sb.append(" AND te.tipoEquipamento = :tipoEquipamento ");
         params.put("tipoEquipamento", filtro.getTipoEquipamento());
      }

   }

   @Override
   public List<TipoExercicio> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      final TipoExercicio filtroTipoExercicio = (TipoExercicio) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT te FROM TipoExercicio te ");
      sb.append(" JOIN FETCH te.tipoEquipamento ");
      sb.append(" JOIN FETCH te.corpo ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtroTipoExercicio);
      }
      if (ordernar != null)
      {
         sb.append("ORDER BY te." + ordernar.getColuna() + " " + ordernar.getOrdernar());
      }
      return findByNamedParams(sb.toString(), params, new Paginacao(first, count));
   }

   @Override
   public List<TipoExercicio> buscaListaPorCorpo(final Corpo corpo)
   {
      final TipoExercicio filtroTipoExercicio = new TipoExercicio();
      filtroTipoExercicio.setCorpo(corpo);
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT te FROM TipoExercicio te ");
      sb.append(" JOIN FETCH te.tipoEquipamento ");
      sb.append(" JOIN FETCH te.corpo ");
      if (filtroTipoExercicio != null)
      {
         montaConsultaGenerica(sb, params, filtroTipoExercicio);
      }

      return consulta(sb.toString(), params);
   }

}
