package com.br.GrandeViaFitness.dao.imp;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Pessoa;

@Repository
public class PessoaDaoImp extends JpaDao<PessoaDao> implements PessoaDao
{
   private static final long serialVersionUID = 1L;

   @Override
   public Pessoa buscaPessoaPorCpf(final String cpf)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append(" SELECT p FROM Pessoa p ");
      sb.append(" WHERE p.cpfPessoa = ? ");
      return (Pessoa) findSingleResult(sb.toString(), cpf);
   }
}
