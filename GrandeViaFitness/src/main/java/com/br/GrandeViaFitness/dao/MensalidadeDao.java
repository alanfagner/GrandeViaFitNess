package com.br.GrandeViaFitness.dao;

import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Mensalidade;

public interface MensalidadeDao extends Dao<Mensalidade>
{

   Integer buscaMensalidade(Mensalidade mensalidade);

}
