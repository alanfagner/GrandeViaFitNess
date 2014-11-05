package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.servico.RlPessoaExercicioServico;

@Named("rlPessoaExercicioAS")
public class RlPessoaExercicioAS implements Provider<RlPessoaExercicio>
{

   @Autowired
   private RlPessoaExercicioServico rlPessoaExercicioServico;

   @Override
   public List<RlPessoaExercicio> buscaListaGrid(final Entidade filtro, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      return rlPessoaExercicioServico.buscaListaGrid(filtro, first, count, ordernar);
   }

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      return rlPessoaExercicioServico.contadorListaGrid(filtro);
   }

   public List<RlPessoaExercicio> buscaListaExercicio(final RlPessoaExercicio rlPessoaExercicio)
   {
      return rlPessoaExercicioServico.buscaListaExercicio(rlPessoaExercicio);
   }

   @Transactional(noRollbackFor = Exception.class)
   public void persisteDados(final RlPessoaExercicio rlPessoaExercicio)
   {
      rlPessoaExercicioServico.persisteDados(rlPessoaExercicio);

   }

}
