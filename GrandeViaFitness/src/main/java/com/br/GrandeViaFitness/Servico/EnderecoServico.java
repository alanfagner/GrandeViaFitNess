package com.br.GrandeViaFitness.Servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.Dao.EnderecoDao;
import com.br.GrandeViaFitness.Model.Endereco;


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
