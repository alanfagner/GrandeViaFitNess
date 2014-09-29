package com.br.GrandeViaFitness.Componentes.provider;

import java.util.List;
import com.br.GrandeViaFitness.Componentes.ParametrosOrdenacao;
import com.br.GrandeViaFitness.Model.Entidade;

public interface Provider<T>
{
   List<T> buscaListaGrid(Entidade entidade, long first, long count, ParametrosOrdenacao ordernar);

   int contadorListaGrid(Entidade entidade);
}
