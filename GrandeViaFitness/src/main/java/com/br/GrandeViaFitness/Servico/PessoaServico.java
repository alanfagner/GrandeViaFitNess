package com.br.GrandeViaFitness.Servico;

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
      return pessoaDao.buscaPessoaPorCpf(cpf);
   }

   public int contadorListaGrid(final Entidade filtro)
   {
      return pessoaDao.contadorListaGrid(filtro);
   }

   public List<Pessoa> buscaListaGrid(final Entidade filtro, final long first, final long count, final ParametrosOrdenacao ordernar)
   {
      return pessoaDao.buscaListaGrid(filtro, first, count, ordernar);
   }

   public List<Pessoa> all(){
      pessoaDao.setPersistentClass(Pessoa.class);
      return pessoaDao.findAll();
   }

   public void save(final Pessoa pessoa)
   {
      pessoaDao.save(pessoa);
   }

}
