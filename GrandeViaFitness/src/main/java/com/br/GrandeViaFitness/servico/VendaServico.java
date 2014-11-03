package com.br.GrandeViaFitness.servico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.VendaDao;
import com.br.GrandeViaFitness.model.Venda;

@Service
public class VendaServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private VendaDao vendaDao;

   public void salvarVenda(final Venda venda)
   {
      getVendaDao().save(venda);
   }

   public VendaDao getVendaDao()
   {
      vendaDao.setPersistentClass(Venda.class);
      return vendaDao;
   }

}
