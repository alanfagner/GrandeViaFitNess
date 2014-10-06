package com.br.GrandeViaFitness.dao.impl;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.TipoEquipamentoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.TipoEquipamento;

@Repository
public class TipoEquipamentoDaoImp extends JpaDao<TipoEquipamento> implements TipoEquipamentoDao
{
   private static final long serialVersionUID = -1325360977814185046L;

}
