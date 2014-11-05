package com.br.GrandeViaFitness.servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.MensalidadeDao;
import com.br.GrandeViaFitness.model.Mensalidade;

@Service
public class MensalidadeServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private MensalidadeDao mensalidadeDao;

   public void save(final Mensalidade mensalidade)
   {
      getMensalidadeDao().save(mensalidade);
   }

   public MensalidadeDao getMensalidadeDao()
   {
      mensalidadeDao.setPersistentClass(Mensalidade.class);
      return mensalidadeDao;
   }

   public Integer buscaMensalidade(final Mensalidade mensalidade)
   {
      return getMensalidadeDao().buscaMensalidade(mensalidade);

   }

}
