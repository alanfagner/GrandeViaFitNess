package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.dao.generic.Dao;
import com.br.GrandeViaFitness.model.TipoEquipamento;

public interface TipoEquipamentoDao extends Dao<TipoEquipamento>
{

   List<TipoEquipamento> buscaListaPorEquipamento(TipoEquipamento auxTipoEqui);

}
