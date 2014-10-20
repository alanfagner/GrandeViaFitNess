package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.RlPessoaExercicioDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;

@Service
public class RlPessoaExercicioServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private RlPessoaExercicioDao rlPessoaExercicioDao;

   public int contadorListaGrid(final Entidade filtro)
   {
      return rlPessoaExercicioDao.contadorListaGrid(filtro);

   }

   public List<RlPessoaExercicio> buscaListaGrid(final Entidade filtro, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      return rlPessoaExercicioDao.buscaListaGrid(filtro, first, count, ordernar);
   }

}
