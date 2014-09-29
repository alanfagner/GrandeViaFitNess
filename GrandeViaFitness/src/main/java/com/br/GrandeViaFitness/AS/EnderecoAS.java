package com.br.GrandeViaFitness.AS;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.GrandeViaFitness.Componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.Componentes.provider.Provider;
import com.br.GrandeViaFitness.Model.Endereco;
import com.br.GrandeViaFitness.Model.Entidade;
import com.br.GrandeViaFitness.Servico.EnderecoServico;

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
