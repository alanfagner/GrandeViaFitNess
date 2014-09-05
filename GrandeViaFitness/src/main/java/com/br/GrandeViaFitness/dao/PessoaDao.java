package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;

public interface PessoaDao
{
   Pessoa buscaPessoaPorCpf(String cpf);

   int contadorListaGrid(Entidade filtro);

   List<Pessoa> buscaListaGrid(Entidade filtro, long first, long count);

   void save(Pessoa pessoa);
}
