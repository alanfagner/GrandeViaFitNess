package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.TipoExercicio;

public interface TipoExercicioDao extends Dao<TipoExercicio>
{

   int contadorListaGrid(Entidade filtro);

   List<TipoExercicio> buscaListaGrid(Entidade filtro, long first, long count, ParametrosOrdenacao ordernar);

   List<TipoExercicio> buscaListaPorCorpo(Corpo corpo);

}
