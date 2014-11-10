package com.br.GrandeViaFitness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.dao.generic.JpaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.utilitario.Paginacao;

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
      final Pessoa filtroPessoa = (Pessoa) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(p.codigo) FROM Pessoa p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtroPessoa);
      }
      return Integer.parseInt(consultaUnicoResultado(sb.toString(), params).toString());
   }

   public void montaConsultaGenerica(final StringBuilder sb, final Map<String, Object> params, final Pessoa filtro)
   {
      sb.append(" WHERE 1 = 1 ");
      if (filtro.getCodigo() != null)
      {
         sb.append(" AND p.codigo = :codigo ");
         params.put("codigo", filtro.getCodigo());
      }
      if (filtro.getNomePessoa() != null)
      {
         sb.append(" AND UPPER(p.nomePessoa) LIKE UPPER(:nome) ");
         params.put("nome", "%" + filtro.getNomePessoa() + "%");
      }
      if (filtro.getCpfPessoa() != null)
      {
         sb.append(" AND p.cpfPessoa = :cpf ");
         params.put("cpf", filtro.getCpfPessoa());
      }
      if (filtro.getEmailPessoa() != null)
      {
         sb.append(" AND UPPER(p.emailPessoa) LIKE UPPER(:email) ");
         params.put("email", "%" + filtro.getEmailPessoa() + "%");
      }
   }

   @Override
   public List<Pessoa> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      final Pessoa filtroPessoa = (Pessoa) filtro;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT p FROM Pessoa p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtroPessoa);
      }
      if (ordernar != null)
      {
         if (ordernar.getColuna().equals("cargoEnum.descricao"))
         {
            sb.append("ORDER BY p.authority " + ordernar.getOrdernar());
         }
         else if (ordernar.getColuna().equals("cpfMascara"))
         {
            sb.append("ORDER BY p.cpfPessoa " + ordernar.getOrdernar());
         }
         else
         {
            sb.append("ORDER BY p." + ordernar.getColuna() + " " + ordernar.getOrdernar());
         }
      }
      return findByNamedParams(sb.toString(), params, new Paginacao(first, count));
   }

   @Override
   public Pessoa buscaCompleta(final Pessoa pessoa)
   {
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT p FROM Pessoa p ");
      sb.append(" JOIN FETCH p.endereco ");
      if (pessoa != null)
      {
         montaConsultaGenerica(sb, params, pessoa);
      }
      return (Pessoa) consultaUnicoResultado(sb.toString(), params);
   }

}
