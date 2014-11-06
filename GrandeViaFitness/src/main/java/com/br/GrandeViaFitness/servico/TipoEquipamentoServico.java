package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.dao.TipoEquipamentoDao;
import com.br.GrandeViaFitness.model.TipoEquipamento;

@Service
public class TipoEquipamentoServico
{
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private TipoEquipamentoDao tipoEquipamentoDao;

   public TipoEquipamentoDao getTipoEquipamentoDao()
   {
      tipoEquipamentoDao.setPersistentClass(TipoEquipamento.class);
      return tipoEquipamentoDao;
   }

   public void save(final TipoEquipamento tipoEquipamento)
   {
      getTipoEquipamentoDao().save(tipoEquipamento);

   }

   public void update(final TipoEquipamento tipoEquipamento)
   {
      getTipoEquipamentoDao().update(tipoEquipamento);

   }

   public List<TipoEquipamento> buscaListaEquipamento()
   {
      return getTipoEquipamentoDao().findAll();
   }

   public void excluirEquipamento(final TipoEquipamento tipoEquipamento)
   {
      getTipoEquipamentoDao().delete(tipoEquipamento.getId());

   }

   public List<TipoEquipamento> buscaListaPorEquipamento(final TipoEquipamento auxTipoEqui)
   {
      return getTipoEquipamentoDao().buscaListaPorEquipamento(auxTipoEqui);
   }
}
