package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Mensalidade;

public interface MensalidadeDao extends Dao<Mensalidade>
{

   Integer buscaMensalidade(Mensalidade mensalidade);

   int contadorListaGrid(Entidade filtro);

   List<Mensalidade> buscaListaGrid(Entidade filtro, long first, long count, ParametrosOrdenacao ordernar);

}
