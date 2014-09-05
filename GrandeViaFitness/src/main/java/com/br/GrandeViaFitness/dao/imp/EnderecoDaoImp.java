package com.br.GrandeViaFitness.dao.imp;

import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.dao.EnderecoDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Endereco;

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
