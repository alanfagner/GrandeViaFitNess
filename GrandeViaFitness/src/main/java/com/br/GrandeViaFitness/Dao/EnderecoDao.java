package com.br.GrandeViaFitness.Dao;

import com.br.GrandeViaFitness.Model.Endereco;

public interface EnderecoDao
{
   Endereco buscaEnderecoPorCEP(String CEP);

   void save(Endereco endereco);
}
