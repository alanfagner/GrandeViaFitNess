package com.br.GrandeViaFitness.dao.imp;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.Utilitario.Paginacao;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;

@Repository
public class PessoaDaoImp extends JpaDao<Pessoa> implements PessoaDao
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

   @Override
   public int contadorListaGrid(final Entidade filtro)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append(" SELECT COUNT(p.codigo) FROM Pessoa p ");
      return Integer.parseInt(findSingleResult(sb.toString()).toString());
   }

   @Override
   public List<Pessoa> buscaListaGrid(final Entidade filtro, final long first, final long count)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append(" SELECT p FROM Pessoa p ");

      return findByNamedParams(sb.toString(), null, new Paginacao(first, count));
   }


}
