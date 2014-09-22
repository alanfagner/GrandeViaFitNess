package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.Servico.AuthorityServico;
import com.br.GrandeViaFitness.Servico.EnderecoServico;
import com.br.GrandeViaFitness.Servico.PessoaServico;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;

@Named("pessoaAS")
public class PessoaAS implements Provider<Pessoa>
{

   @Autowired
   private PessoaServico pessoaServico;
   @Autowired
   private EnderecoServico enderecoServico;
   @Autowired
   private AuthorityServico authorityServico;

   public Pessoa buscaPessoaPorCpf(final String Cpf)
   {
      return pessoaServico.buscaPessoaPorCpf(Cpf);
   }
   @Override
   public List<Pessoa> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return pessoaServico.buscaListaGrid(filtro, first, count, ordernar);
   }
   @Override
   public int contadorListaGrid(final Entidade Filtro)
   {
      return pessoaServico.contadorListaGrid(Filtro);
   }
   public PessoaServico getPessoaServico()
   {
      return pessoaServico;
   }
   public EnderecoServico getEnderecoServico()
   {
      return enderecoServico;
   }
   public AuthorityServico getAuthorityServico()
   {
      return authorityServico;
   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void saveInicial(final Pessoa pessoa)
   {
      authorityServico.save(pessoa.getAuthority());
      enderecoServico.save(pessoa.getEndereco());
      pessoaServico.save(pessoa);

   }

   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
   public void save(final Pessoa pessoa)
   {
      if (pessoa.getEndereco().getCodigo() == 0)
      {
         enderecoServico.save(pessoa.getEndereco());
      }
      pessoaServico.save(pessoa);

   }

}
