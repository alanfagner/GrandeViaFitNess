package com.br.GrandeViaFitness.Dao;

import java.util.List;
import com.br.GrandeViaFitness.Componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.Dao.Generic.Dao;
import com.br.GrandeViaFitness.Model.Entidade;
import com.br.GrandeViaFitness.Model.Pessoa;

public interface PessoaDao extends Dao<Pessoa>
{
   Pessoa buscaPessoaPorCpf(String cpf);

   int contadorListaGrid(Entidade filtro);

   List<Pessoa> buscaListaGrid(Entidade filtro, long first, long count, ParametrosOrdenacao ordernar);

   @Override
   void save(Pessoa pessoa);

   Pessoa buscaCompleta(Pessoa pessoa);
}
