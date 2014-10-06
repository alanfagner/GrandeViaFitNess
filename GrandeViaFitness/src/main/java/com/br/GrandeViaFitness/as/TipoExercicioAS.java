package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.servico.TipoEquipamentoServico;

@Named("tipoExercicioAS")
public class TipoExercicioAS implements Provider<TipoExercicio>
{

   @Autowired
   private TipoEquipamentoServico tipoEquipamentoServico;
   @Override
   public List<TipoExercicio> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return null;
   }

   @Override
   public int contadorListaGrid(final Entidade entidade)
   {
      return 0;
   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void persisteDadosEquipamento(final TipoEquipamento tipoEquipamento)
   {
      if (tipoEquipamento.getCodigo() == null)
      {

         tipoEquipamentoServico.save(tipoEquipamento);
      }
      else
      {
         tipoEquipamentoServico.update(tipoEquipamento);
      }
   }

   public List<TipoEquipamento> buscaListaEquipamento()
   {

      return tipoEquipamentoServico.buscaListaEquipamento();
   }

}
