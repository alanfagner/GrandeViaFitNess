package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlProdutoVenda;

public interface RLProdutoVendaDao extends Dao<RlProdutoVenda>
{

   List<RlProdutoVenda> buscaListaGrid(Entidade entidade, long first, long count, ParametrosOrdenacao ordernar);

   int contadorListaGrid(Entidade entidade);

   List<RlProdutoVenda> buscaListaProdutoVendaPorCodigoVenda(Long codigo);

}
