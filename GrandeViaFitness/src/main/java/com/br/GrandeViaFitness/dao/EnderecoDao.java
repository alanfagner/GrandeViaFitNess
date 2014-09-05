package com.br.GrandeViaFitness.dao;

import com.br.GrandeViaFitness.model.Endereco;

public interface EnderecoDao
{
   Endereco buscaEnderecoPorCEP(String CEP);

   void save(Endereco endereco);
}
