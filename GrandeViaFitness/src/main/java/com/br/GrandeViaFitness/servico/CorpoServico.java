package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.CorpoDao;
import com.br.GrandeViaFitness.model.Corpo;

@Service
public class CorpoServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private CorpoDao corpoDao;

   public CorpoDao getCorpoDao()
   {
      corpoDao.setPersistentClass(Corpo.class);
      return corpoDao;
   }

   public void save(final Corpo corpo)
   {
      getCorpoDao().save(corpo);

   }

   public void update(final Corpo corpo)
   {
      getCorpoDao().update(corpo);

   }

   public List<Corpo> buscaListaCorpo()
   {
      return getCorpoDao().findAll();
   }

   public void excluirCorpo(final Corpo corpo)
   {
      getCorpoDao().delete(corpo.getId());

   }

   public List<Corpo> buscaListaPorCorpo(final Corpo corpo)
   {
      return getCorpoDao().buscaListaPorCorpo(corpo);
   }

}
