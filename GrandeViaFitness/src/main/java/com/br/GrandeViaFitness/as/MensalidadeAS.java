package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Mensalidade;
import com.br.GrandeViaFitness.servico.MensalidadeServico;

@Named("mensalidadeAS")
public class MensalidadeAS implements Provider<Mensalidade>
{
   @Autowired
   private MensalidadeServico mensalidadeServico;
   @Override
   public List<Mensalidade> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
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

   @Transactional(noRollbackFor = Exception.class)
   public void save(final Mensalidade mensalidade)
   {
      mensalidadeServico.save(mensalidade);
   }

   public Integer buscaMensalidade(final Mensalidade mensalidade)
   {
      return mensalidadeServico.buscaMensalidade(mensalidade);
   }

}
