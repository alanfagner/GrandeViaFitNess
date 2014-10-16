package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.servico.CorpoServico;

@Named("corpoAS")
public class CorpoAS implements Provider<Corpo>
{
   @Autowired
   private CorpoServico corpoServico;

   @Override
   public List<Corpo> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
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

   public List<Corpo> recuperaListaCorpo()
   {
      return corpoServico.buscaListaCorpo();
   }
}
