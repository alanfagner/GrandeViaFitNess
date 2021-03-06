package com.br.GrandeViaFitness.componentes.provider;

import java.util.List;
import com.br.GrandeViaFitness.componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.model.Entidade;

public interface Provider<T>
{
   List<T> buscaListaGrid(Entidade entidade, long first, long count, ParametrosOrdenacao ordernar);

   int contadorListaGrid(Entidade entidade);
}
