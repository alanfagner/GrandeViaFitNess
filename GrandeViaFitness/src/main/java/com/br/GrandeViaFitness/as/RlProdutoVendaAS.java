package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.servico.RLProdutoVendaServico;

@Named("rlProdutoVendaAS")
public class RlProdutoVendaAS implements Provider<RlProdutoVenda>
{

   @Autowired
   private RLProdutoVendaServico rlProdutoVendaServico;
   @Override
   public List<RlProdutoVenda> buscaListaGrid(final Entidade entidade, final long first, final long count,
      final ParametrosOrdenacao ordernar)
   {
      return rlProdutoVendaServico.buscaListaGrid(entidade, first, count, ordernar);
   }

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      return rlProdutoVendaServico.contadorListaGrid(entidade);
   }

}
