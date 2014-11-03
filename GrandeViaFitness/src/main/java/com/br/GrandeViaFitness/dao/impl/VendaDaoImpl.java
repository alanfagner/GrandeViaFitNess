package com.br.GrandeViaFitness.dao.impl;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.VendaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Venda;

@Repository
public class VendaDaoImpl extends JpaDao<Venda> implements VendaDao
{
   private static final long serialVersionUID = -730138955344653556L;

}
