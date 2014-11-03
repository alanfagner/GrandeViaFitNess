package com.br.GrandeViaFitness.dao.impl;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.RLProdutoVendaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.RlProdutoVenda;

@Repository
public class RLProdutoVendaDaoImpl extends JpaDao<RlProdutoVenda> implements RLProdutoVendaDao
{
   private static final long serialVersionUID = 5364397220727102343L;

}
