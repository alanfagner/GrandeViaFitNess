package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Corpo;

public interface CorpoDao extends Dao<Corpo>
{

   List<Corpo> buscaListaPorCorpo(Corpo corpo);

}
