package com.br.GrandeViaFitness.Dao.Impl;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.Dao.EnderecoDao;
import com.br.GrandeViaFitness.Dao.Generic.JpaDao;
import com.br.GrandeViaFitness.Model.Endereco;

@Repository
public class EnderecoDaoImp extends JpaDao<Endereco> implements EnderecoDao
{
   private static final long serialVersionUID = 1170543987088922233L;

   @Override
   public Endereco buscaEnderecoPorCEP(final String CEP)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append(" SELECT e FROM Endereco e ");
      sb.append(" WHERE e.cep Like ? ");
      return (Endereco) findSingleResult(sb.toString(), CEP);
   }

}
