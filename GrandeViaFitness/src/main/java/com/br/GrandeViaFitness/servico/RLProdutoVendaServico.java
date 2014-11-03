package com.br.GrandeViaFitness.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.RLProdutoVendaDao;
import com.br.GrandeViaFitness.model.RlProdutoVenda;

@Service
public class RLProdutoVendaServico
{
   @Autowired
   private RLProdutoVendaDao rlProdutoVendaDao;

   public void salva(final RlProdutoVenda rlProdutoVenda)
   {
      getRlProdutoVendaDao().save(rlProdutoVenda);
   }

   public RLProdutoVendaDao getRlProdutoVendaDao()
   {
      rlProdutoVendaDao.setPersistentClass(RlProdutoVenda.class);
      return rlProdutoVendaDao;
   }

}
