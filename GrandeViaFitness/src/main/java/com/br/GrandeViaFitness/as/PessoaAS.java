package com.br.GrandeViaFitness.as;

import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.componentes.provider.Provider;
import com.br.GrandeViaFitness.model.Authority;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.servico.AuthorityServico;
import com.br.GrandeViaFitness.servico.EnderecoServico;
import com.br.GrandeViaFitness.servico.PessoaServico;

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

   @Transactional
   public void saveInicial(final Pessoa pessoa)
   {
      authorityServico.save(pessoa.getAuthority());
      final Authority authority = new Authority();
      authority.setAuthority("R_CLI");
      authority.setCodigo(2);
      authorityServico.save(pessoa.getAuthority());
      enderecoServico.save(pessoa.getEndereco());
      pessoaServico.save(pessoa);

   }

   @Transactional
   public void save(final Pessoa pessoa)
   {
      if (pessoa.getEndereco().getCodigo() == 0)
      {
         enderecoServico.save(pessoa.getEndereco());
      }

      if(pessoa.getCodigo() == null){
         pessoaServico.save(pessoa);
      }else{
         pessoaServico.alterar(pessoa);
      }
   }

   public Pessoa buscaCompleta(final Pessoa pessoa)
   {
      return pessoaServico.buscaCompleta(pessoa);

   }

   @Transactional
   public void excluirPessoa(final Pessoa pessoa)
   {
      pessoaServico.excluir(pessoa);

   }

}
