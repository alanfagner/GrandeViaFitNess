package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.VendaDao;
import com.br.GrandeViaFitness.model.Entidade;
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

   public int contadorListaGrid(final Entidade entidade)
   {
      return getVendaDao().contadorListaGrid(entidade);
   }

   public List<Venda> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return getVendaDao().buscaListaGrid(entidade, first, count, ordernar);
   }

   public void excluir(final Venda entidade)
   {
      getVendaDao().delete(entidade.getId());

   }

   public String calculaSaldo(final Venda venda)
   {
      return getVendaDao().calculaSaldo(venda);
   }

}
