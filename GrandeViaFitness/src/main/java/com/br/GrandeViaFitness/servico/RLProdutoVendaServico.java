package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.RLProdutoVendaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Produto;
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

   public List<RlProdutoVenda> buscaListaGrid(final Entidade entidade, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      return getRlProdutoVendaDao().buscaListaGrid(entidade, first, count, ordernar);
   }

   public int contadorListaGrid(final Entidade entidade)
   {
      return getRlProdutoVendaDao().contadorListaGrid(entidade);
   }

   public List<RlProdutoVenda> buscaListaProdutoVendaPorCodigoVenda(final Long codigo)
   {
      return getRlProdutoVendaDao().buscaListaProdutoVendaPorCodigoVenda(codigo);
   }

   public void excluir(final Long codigo)
   {
      getRlProdutoVendaDao().delete(codigo);
   }

   public List<RlProdutoVenda> buscaListaPorProduto(final Produto produto)
   {
      return getRlProdutoVendaDao().buscaListaPorProduto(produto);

   }

}
