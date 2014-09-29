package com.br.GrandeViaFitness.Servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.Dao.AuthorityDao;
import com.br.GrandeViaFitness.Model.Authority;


@Service
public class AuthorityServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private AuthorityDao authorityDao;

   public Authority findAuthority(final String authority)
   {
      logger.debug("Busca por Authority: " + authority);
      return authorityDao.findAuthority(authority);
   }

   public void save(final Authority authority)
   {
      authorityDao.save(authority);
   }

}
