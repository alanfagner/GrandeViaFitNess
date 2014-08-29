package com.br.GrandeViaFitness.componentes;

import java.util.List;

import com.br.GrandeViaFitness.model.Entidade;

public interface ProviderGenerico<T>
{
    List<T> buscaListaGrid(Entidade entidade, long first, long count);

    int contadorListaGrid(Entidade entidade);
}