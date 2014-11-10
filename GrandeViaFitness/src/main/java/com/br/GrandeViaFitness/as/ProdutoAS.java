package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.servico.ProdutoServico;

@Named("produtoAS")
public class ProdutoAS implements Provider<Produto>
{
   @Autowired
   private ProdutoServico produtoServico;
   @Override
   public List<Produto> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return produtoServico.buscaListaGrid(entidade, first, count, ordernar);
   }

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      return produtoServico.contadorListaGrid(entidade);
   }

   @Transactional
   public void persisteDados(final Produto produto)
   {
      if (produto.getCodigo() == null)
      {
         produtoServico.salva(produto);
      }
      else
      {
         produtoServico.alterar(produto);
      }

   }

   @Transactional
   public void excluirPessoa(final Produto produto)
   {
      produtoServico.excluirPessoa(produto);

   }

}
