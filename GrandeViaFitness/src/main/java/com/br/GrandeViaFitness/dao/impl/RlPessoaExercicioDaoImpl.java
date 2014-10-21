package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.RlPessoaExercicioDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.utilitario.Paginacao;

@Repository
public class RlPessoaExercicioDaoImpl extends JpaDao<RlPessoaExercicio> implements RlPessoaExercicioDao
{
   private static final long serialVersionUID = 2916108927047949246L;

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {

      final RlPessoaExercicio rlPessoaExercicio = (RlPessoaExercicio) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(rlp.codigo) FROM RlPessoaExercicio rlp ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, rlPessoaExercicio);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final RlPessoaExercicio rlPessoaExercicio)
   {
      sb.append(" WHERE 1 = 1 ");
      if (rlPessoaExercicio.getTipoExercicio() != null)
      {
         sb.append(" AND rlp.tipoExercicio = :tipoExercico ");
         params.put("tipoExercico", rlPessoaExercicio.getTipoExercicio());
      }

      if (rlPessoaExercicio.getPessoa() != null)
      {
         sb.append(" AND rlp.pessoa = :pessoa ");
         params.put("pessoa", rlPessoaExercicio.getPessoa());
      }

   }

   @Override
   public List<RlPessoaExercicio> buscaListaGrid(final Entidade filtro, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      final RlPessoaExercicio rlPessoaExercicio = (RlPessoaExercicio) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT rlp.codigo FROM RlPessoaExercicio rlp ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, rlPessoaExercicio);
      }
      if (ordernar != null)
      {
         sb.append("ORDER BY rlp." + ordernar.getColuna() + " " + ordernar.getOrdernar());
      }
      return findByNamedParams(sb.toString(), params, new Paginacao(first, count));
   }

   @Override
   public List<RlPessoaExercicio> buscaListaExercicio(final RlPessoaExercicio rlPessoaExercicio)
   {
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT rlp FROM RlPessoaExercicio rlp ");
      if (rlPessoaExercicio != null)
      {
         montaConsultaGenerica(sb, params, rlPessoaExercicio);
      }
      sb.append("ORDER BY rlp.dataExercicio desc");
      return consulta(sb.toString(), params);
   }

}
