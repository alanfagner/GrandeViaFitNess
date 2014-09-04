package com.br.GrandeViaFitness.dao;

import com.br.GrandeViaFitness.model.Pessoa;

public interface PessoaDao
{
   Pessoa buscaPessoaPorCpf(String cpf);
}
