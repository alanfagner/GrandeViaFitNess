package com.br.GrandeViaFitness.servico;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.dao.PessoaDao;
import com.br.GrandeViaFitness.model.Entidade;
import com.br.GrandeViaFitness.model.Pessoa;


@Service
public class PessoaServico
{

   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   public PessoaDao pessoaDao;

   public Pessoa buscaPessoaPorCpf(final String cpf)
   {
      logger.debug("Busca por cpf: " + cpf);
      return getPessoaDao().buscaPessoaPorCpf(cpf);
   }

   public PessoaDao getPessoaDao()
   {
      pessoaDao.setPersistentClass(Pessoa.class);
      return pessoaDao;
   }

   public int contadorListaGrid(final Entidade filtro)
   {
      return getPessoaDao().contadorListaGrid(filtro);
   }

   public List<Pessoa> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return getPessoaDao().buscaListaGrid(filtro, first, count, ordernar);
   }

   public List<Pessoa> all(){

      return getPessoaDao().findAll();
   }

   public void save(final Pessoa pessoa)
   {
      getPessoaDao().save(pessoa);
   }

   public void alterar(final Pessoa pessoa)
   {
      getPessoaDao().update(pessoa);
   }

   public Pessoa buscaCompleta(final Pessoa pessoa)
   {
      return getPessoaDao().buscaCompleta(pessoa);
   }

   public void excluir(final Pessoa pessoa)
   {
      getPessoaDao().delete(pessoa.getId());
   }

}
