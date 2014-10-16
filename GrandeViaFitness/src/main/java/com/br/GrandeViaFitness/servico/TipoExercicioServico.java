package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.TipoExercicioDao;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.TipoExercicio;

@Service
public class TipoExercicioServico
{

   Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private TipoExercicioDao tipoExercicioDao;

   public TipoExercicioDao getTipoExercicioDao()
   {
      tipoExercicioDao.setPersistentClass(TipoExercicio.class);
      return tipoExercicioDao;
   }

   public void save(final TipoExercicio tipoExercicio)
   {
      getTipoExercicioDao().save(tipoExercicio);

   }

   public void update(final TipoExercicio tipoExercicio)
   {
      getTipoExercicioDao().update(tipoExercicio);

   }

   public int contadorListaGrid(final Entidade filtro)
   {
      return getTipoExercicioDao().contadorListaGrid(filtro);
   }

   public List<TipoExercicio> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return getTipoExercicioDao().buscaListaGrid(filtro, first, count, ordernar);
   }

   public void excluirExercico(final TipoExercicio tipoExercicio)
   {
      getTipoExercicioDao().delete(tipoExercicio.getId());

   }

   public List<TipoExercicio> buscaListaPorCorpo(final Corpo corpo)
   {
      return getTipoExercicioDao().buscaListaPorCorpo(corpo);
   }

}
