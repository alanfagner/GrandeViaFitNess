package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.model.TipoEquipamento;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.servico.CorpoServico;
import com.br.GrandeViaFitness.servico.RlPessoaExercicioServico;
import com.br.GrandeViaFitness.servico.TipoEquipamentoServico;
import com.br.GrandeViaFitness.servico.TipoExercicioServico;

@Named("tipoExercicioAS")
public class TipoExercicioAS implements Provider<TipoExercicio>
{

   @Autowired
   private TipoExercicioServico tipoExercicioServico;

   @Autowired
   private CorpoServico corpoServico;

   @Autowired
   private RlPessoaExercicioServico rlPessoaExercicioServico;

   @Autowired
   private TipoEquipamentoServico tipoEquipamentoServico;

   public List<TipoExercicio> buscaListaTipoExercicioPorCorpo(final Corpo corpo)
   {
      return tipoExercicioServico.buscaListaPorCorpo(corpo);
   }

   public List<Corpo> buscaListaCorpoPorCorpo(final Corpo corpo)
   {
      return corpoServico.buscaListaPorCorpo(corpo);
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

   @Transactional
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

   @Transactional
   public void excluirEquipamento(final TipoEquipamento tipoEquipamento)
   {
      tipoEquipamentoServico.excluirEquipamento(tipoEquipamento);

   }

   @Transactional
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

   @Transactional
   public void excluirCorpo(final Corpo corpo)
   {
      corpoServico.excluirCorpo(corpo);

   }

   @Transactional
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

   @Transactional
   public void excluirExercicio(final TipoExercicio tipoExercicio)
   {
      tipoExercicioServico.excluirExercico(tipoExercicio);

   }

   public List<TipoEquipamento> buscaListaEquipamento(final TipoEquipamento auxTipoEqui)
   {
      return tipoEquipamentoServico.buscaListaPorEquipamento(auxTipoEqui);
   }

   public List<TipoExercicio> buscaListaTipoExercicioPorTipoExerciciop(final TipoExercicio auxTipo)
   {
      return tipoExercicioServico.buscaListaTipoExercicioPorTipoExerciciop(auxTipo);
   }

   public boolean verificaHistorio(final TipoExercicio tipoExercicio)
   {
      Boolean valida = true;
      final RlPessoaExercicio auxPessoaExercicio = new RlPessoaExercicio();
      auxPessoaExercicio.setTipoExercicio(tipoExercicio);
      if (rlPessoaExercicioServico.buscaListaExercicio(auxPessoaExercicio).size() > 0)
      {
         valida = false;
      }

      return valida;

   }

   public boolean verificaHistoricoEquipamento(final TipoEquipamento tipoEquipamento)
   {
      Boolean valida = true;
      final TipoExercicio auxTipoExercicio = new TipoExercicio();
      auxTipoExercicio.setTipoEquipamento(tipoEquipamento);
      if (tipoExercicioServico.buscaListaTipoExercicioPorTipoExerciciop(auxTipoExercicio).size() > 0)
      {
         valida = false;
      }
      return valida;
   }

   public boolean verficaHistoricoCorpo(final Corpo corpo)
   {
      Boolean valida = true;
      if (tipoExercicioServico.buscaListaPorCorpo(corpo).size() > 0)
      {
         valida = false;
      }
      return valida;
   }

}
