package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlProdutoVenda;
import com.br.GrandeViaFitness.model.Venda;
import com.br.GrandeViaFitness.servico.RLProdutoVendaServico;
import com.br.GrandeViaFitness.servico.VendaServico;

@Named("vendaAS")
public class VendaAS implements Provider<Venda>
{
   @Autowired
   private VendaServico vendaServico;

   @Autowired
   private RLProdutoVendaServico rlProdutoVendaServico;

   @Override
   public List<Venda> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Transactional
   public void salvarVenda(final List<RlProdutoVenda> auxProdutoVenda, final Venda venda)
   {

      for (final RlProdutoVenda rlProdutoVenda : auxProdutoVenda)
      {
         if (venda.getCodigo() == null)
         {
            vendaServico.salvarVenda(venda);
         }
         rlProdutoVenda.setVenda(venda);
         rlProdutoVendaServico.salva(rlProdutoVenda);
      }
   }


}
