package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.servico.EnderecoServico;

@Named("enderecoAS")
public class EnderecoAS implements Provider<Endereco>
{
   @Autowired
   private EnderecoServico enderecoServico;

   public Endereco buscaEnderecoPorCEP(final String CEP)
   {
      return enderecoServico.buscaEnderecoPorCEP(CEP);
   }
   @Override
   public List<Endereco> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernacao)
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

}
