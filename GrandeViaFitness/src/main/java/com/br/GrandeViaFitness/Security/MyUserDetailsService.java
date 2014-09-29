package com.br.GrandeViaFitness.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.Dao.Impl.UserDaoImp;

@Service
public class MyUserDetailsService implements UserDetailsService
{

   Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private UserDaoImp userDao;

   @Override
   public UserDetails loadUserByUsername(final String cpf)
   {
      logger.debug("usrname to find : " + cpf);
      return userDao.findByUser(cpf);
   }

}
