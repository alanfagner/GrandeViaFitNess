package com.br.GrandeViaFitness.Servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.model.Pessoa;

@Service
public class PessoaServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   public PessoaDao pessoaDao;

   public Pessoa buscaPessoaPorCpf(final String cpf)
   {
      logger.debug("Busca por email: " + cpf);
      return pessoaDao.buscaPessoaPorCpf(cpf);
   }
}
