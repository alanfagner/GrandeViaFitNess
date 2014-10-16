package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.servico.CorpoServico;
import com.br.GrandeViaFitness.servico.TipoEquipamentoServico;
import com.br.GrandeViaFitness.servico.TipoExercicioServico;

@Named("tipoExercicioAS")
public class TipoExercicioAS implements Provider<TipoExercicio>
{

   @Autowired
   private TipoEquipamentoServico tipoEquipamentoServico;

   @Autowired
   private TipoExercicioServico tipoExercicioServico;

   @Autowired
   private CorpoServico corpoServico;

   public List<TipoExercicio> buscaListaPorCorpo(final Corpo corpo)
   {
      return tipoExercicioServico.buscaListaPorCorpo(corpo);
   }
   @Override
   public List<TipoExercicio> buscaListaGrid(final Entidade entidade, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return tipoExercicioServico.buscaListaGrid(entidade, first, count, ordernar);
   }

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      return tipoExercicioServico.contadorListaGrid(filtro);
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
   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void excluirEquipamento(final TipoEquipamento tipoEquipamento)
   {
      tipoEquipamentoServico.excluirEquipamento(tipoEquipamento);

   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void persisteDadosCorpo(final Corpo corpo)
   {
      if (corpo.getCodigo() == null)
      {

         corpoServico.save(corpo);
      }
      else
      {
         corpoServico.update(corpo);
      }

   }

   public List<Corpo> buscaListaCorpo()
   {
      return corpoServico.buscaListaCorpo();
   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void excluirCorpo(final Corpo corpo)
   {
      corpoServico.excluirCorpo(corpo);

   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void persisteDadosExercicios(final TipoExercicio tipoExercicio)
   {
      if (tipoExercicio.getCodigo() == null)
      {

         tipoExercicioServico.save(tipoExercicio);
      }
      else
      {
         tipoExercicioServico.update(tipoExercicio);
      }

   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void excluirExercicio(final TipoExercicio tipoExercicio)
   {
      tipoExercicioServico.excluirExercico(tipoExercicio);

   }

}
