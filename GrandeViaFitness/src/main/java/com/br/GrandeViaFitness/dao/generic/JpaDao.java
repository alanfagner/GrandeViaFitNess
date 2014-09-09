package com.br.GrandeViaFitness.dao.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.Utilitario.Paginacao;

@Repository
public class JpaDao<T> implements Dao<T>
{
   private static final long serialVersionUID = 1594193643950624158L;

   private Class<T> persistentClass;

   @PersistenceContext
   private EntityManager entityManager;

   public void setValoresDoResult(final Paginacao paginacao, final Query query)
   {
      if (paginacao != null)
      {
         if (paginacao.getPosicao() != null)
         {
            query.setFirstResult(paginacao.getPosicao());
         }
         if (paginacao.getLimite() != null)
         {
            query.setMaxResults(paginacao.getLimite());
         }
      }
   }

   @Override
   public void delete(final Serializable id)
   {
      this.entityManager.remove(findById(id));
   }

   private Query createQuery(final String queryStr, final Object... params)
   {
      final Query query = this.entityManager.createQuery(queryStr);
      setQueryParams(query, params);
      return query;
   }

   @Override
   public List<?> find(final String queryStr, final Object... params)
   {
      return find(queryStr, null, params);
   }

   @Override
   public List<T> findAll()
   {
      return null;
   }

   @Override
   public T findById(final Serializable id)
   {
      return this.entityManager.find(getPersistentClass(), id);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> findByNamedParams(final String queryname, final Map<String, Object> params, final Paginacao paginacao)
   {
      final Query query = this.entityManager.createQuery(queryname);
      setQueryParams(query, params);
      setValoresDoResult(paginacao, query);
      return query.getResultList();
   }

   @Override
   public List<T> findByNamedQuery(final String namedQuery, final Object... params)
   {
      return findByNamedQuery(namedQuery, null, params);
   }

   @Override
   public List<T> findByNativeQuery(final String sql, final Object... params)
   {
      return findByNativeQuery(sql, null, params);
   }

   @Override
   public Object findSingleResult(final String queryStr, final Object... params)
   {
      try
      {
         return createQuery(queryStr, params).getSingleResult();
      }
      catch (final NoResultException e)
      {
         return null;
      }

   }

   @Override
   public void refresh(final T entity)
   {
      this.entityManager.refresh(entity);
   }

   @Override
   public void save(final T obj)
   {
      this.entityManager.persist(obj);
   }

   @Override
   public void setPersistentClass(final Class<T> persistentClass)
   {
      this.persistentClass = persistentClass;
   }

   @Override
   public void update(final T obj)
   {
      this.entityManager.merge(obj);
   }

   public final Class<T> getPersistentClass()
   {
      if (persistentClass == null)
      {
         throw new RuntimeException(
            "É necessário invocar o método setPersistentClass(Class<T> clazz)");
      }
      return persistentClass;
   }

   /* private void paginar(final Paginacao paginacao, final Query query)
    {
       if (paginacao != null)
       {
          if (paginacao.getPosicao() != null)
          {
             query.setFirstResult(paginacao.getPosicao());
          }
          if (paginacao.getLimite() != null)
          {
             query.setMaxResults(paginacao.getLimite());
          }
       }
    }
   */
   private void setQueryParams(final Query query, final Object... params)
   {
      if (params != null && params.length > 0)
      {
         int i = 0;
         for (final Object param : params)
         {
            if (param != null)
            {
               if ((param instanceof String && ((String) param).trim().isEmpty()))
               {
                  continue;
               }
               i++;
               query.setParameter(i, param);
            }
         }
      }
   }

   private void setQueryParams(final Query query, final Map<String, Object> params)
   {
      if (params != null)
      {
         for (final Entry<String, Object> entry : params.entrySet())
         {
            query.setParameter(entry.getKey(), entry.getValue());
         }
      }
   }

}
