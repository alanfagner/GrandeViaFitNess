package com.br.GrandeViaFitness.Dao.Impl;

import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.Dao.AuthorityDao;
import com.br.GrandeViaFitness.Dao.Generic.JpaDao;
import com.br.GrandeViaFitness.Model.Authority;

@Repository
public class AuthorityDaoImp extends JpaDao<Authority> implements AuthorityDao
{
   private static final long serialVersionUID = -3187727338571992115L;

   @Override
   public Authority findAuthority(final String authority)
   {
      try
      {
         final StringBuilder sb = new StringBuilder();
         sb.append("SELECT a from Authority a where a.authority = ?");
         return (Authority) findSingleResult(sb.toString(), authority);
      }
      catch (final NoResultException e)
      {
         return null;
      }
   }

}
