package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.ProdutoDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Produto;

@Service
public class ProdutoServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ProdutoDao produtoDao;

   public void salva(final Produto produto)
   {
      getProdutoDao().save(produto);
   }

   public ProdutoDao getProdutoDao()
   {
      produtoDao.setPersistentClass(Produto.class);
      return produtoDao;
   }

   public void alterar(final Produto produto)
   {
      getProdutoDao().update(produto);
   }

   public List<Produto> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return getProdutoDao().buscaListaGrid(entidade, first, count, ordernar);
   }

   public int contadorListaGrid(final Entidade entidade)
   {
      return getProdutoDao().contadorListaGrid(entidade);
   }

   public void excluirPessoa(final Produto produto)
   {
      getProdutoDao().delete(produto.getId());

   }

}
