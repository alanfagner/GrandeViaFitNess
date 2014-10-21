package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;

public interface RlPessoaExercicioDao extends Dao<RlPessoaExercicio>
{

   int contadorListaGrid(Entidade filtro);

   List<RlPessoaExercicio> buscaListaGrid(Entidade filtro, long first, long count, ParametrosOrdenacao ordernar);

   List<RlPessoaExercicio> buscaListaExercicio(RlPessoaExercicio rlPessoaExercicio);

}
