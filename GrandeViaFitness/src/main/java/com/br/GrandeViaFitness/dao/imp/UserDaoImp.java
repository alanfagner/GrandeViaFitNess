package com.br.GrandeViaFitness.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.dao.UserDao;
import com.br.GrandeViaFitness.model.Authority;
import com.br.GrandeViaFitness.model.User;

@Repository
public class UserDaoImp implements UserDao
{
   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public User findByUser(final String cpf)
   {
      try
      {
         final Query query =
            entityManager
               .createQuery("select new com.br.GrandeViaFitness.model.User(p.cpfPessoa,p.senhaPessoa,p.authority.authority) from Pessoa p WHERE p.cpfPessoa = :cpf");
         query.setParameter("cpf", cpf);
         return (User) query.getSingleResult();
      }
      catch (final Exception e)
      {
         return null;
      }

   }

   @Override
   public Authority findAuthority(final String authority)
   {
      try
      {
         return entityManager
            .createQuery("from Authority where authority = :autohrity", Authority.class)
            .setParameter("authority", authority).getSingleResult();
      }
      catch (final Exception e)
      {
         return null;
      }
   }

   @Override
   public List<User> findAll()
   {
      return entityManager.createQuery("from User", User.class).getResultList();
   }

   public List<User> findAllSortedBy(final String sort, final String order, final long from,
      final long to)
   {
      return entityManager.createQuery("from User u order by " + sort + " " + order, User.class)
         .setFirstResult((int) from).setMaxResults((int) to).getResultList();
   }

   @Transactional
   public void save(final User user)
   {
      for (Authority authority : user.getAuthorities())
      {
         Authority temp;
         if ((temp = findAuthority(authority.getAuthority())) != null)
         {
            authority = temp;
         }
      }
   }

   @Override
   @Transactional
   public boolean save(final Authority authority)
   {
      entityManager.merge(authority);
      return true;
   }

}
