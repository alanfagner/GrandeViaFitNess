package com.br.GrandeViaFitness.servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.EnderecoDao;
import com.br.GrandeViaFitness.model.Endereco;


@Service
public class EnderecoServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private EnderecoDao enderecoDao;

   public Endereco buscaEnderecoPorCEP(final String CEP)
   {
      return enderecoDao.buscaEnderecoPorCEP(CEP);
   }

   public void save(final Endereco endereco)
   {
      enderecoDao.save(endereco);

   }
}
