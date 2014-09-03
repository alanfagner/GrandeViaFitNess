package com.br.GrandeViaFitness.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.model.Pessoa;

@Service
public class PessoaServico
{
   @Autowired
   public PessoaDao pessoaDao;

   public Pessoa buscaPessoaPorCpf(final String cpf)
   {
      return pessoaDao.buscaPessoaPorCpf(cpf);
   }
}
