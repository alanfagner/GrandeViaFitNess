package com.br.GrandeViaFitness.dao.imp;

import java.util.HashMap;
import java.util.Map;
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
      final Map<String, Object> parametros = new HashMap<String, Object>();
      sb.append(" SELECT p Pessoa p ");
      sb.append(" WHERE p.cpfPessoa = :cpf ");
      parametros.put("cpf", cpf);
      return (Pessoa) findSingleResult(sb.toString(), parametros);
   }
}
