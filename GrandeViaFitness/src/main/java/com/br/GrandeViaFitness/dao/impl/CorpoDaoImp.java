package com.br.GrandeViaFitness.dao.impl;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.CorpoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Corpo;

@Repository
public class CorpoDaoImp extends JpaDao<Corpo> implements CorpoDao
{
   private static final long serialVersionUID = -3508897487537738086L;

}
