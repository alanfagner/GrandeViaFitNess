package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;

public interface PessoaDao extends Dao<Pessoa>
{
   Pessoa buscaPessoaPorCpf(String cpf);

   int contadorListaGrid(Entidade filtro);

   List<Pessoa> buscaListaGrid(Entidade filtro, long first, long count);

   @Override
   void save(Pessoa pessoa);
}
