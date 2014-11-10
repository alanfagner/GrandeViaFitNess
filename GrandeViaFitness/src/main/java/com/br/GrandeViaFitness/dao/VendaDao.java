package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Venda;

public interface VendaDao extends Dao<Venda>
{

   int contadorListaGrid(Entidade entidade);

   List<Venda> buscaListaGrid(Entidade entidade, long first, long count, ParametrosOrdenacao ordernar);

   String calculaSaldo(Venda venda);

}
