package com.br.GrandeViaFitness.as;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Named;

import com.br.GrandeViaFitness.componentes.ProviderGenerico;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Entidade;

@Named("enderecoAS")
public class EnderecoAS implements ProviderGenerico<Endereco>
{
    private static Random rand = new Random();
    private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ".toCharArray();

    @Override
    public int contadorListaGrid(final Entidade entidade)
    {
        return 10;
    }

    @Override
    public List<Endereco> buscaListaGrid(final Entidade entidade, final long first, final long count)
    {
        final List<Endereco> listaEndereco = new ArrayList<Endereco>();

        for (long i = 0; i < count; i++)
        {
            final Endereco endereco = new Endereco();
            endereco.setCidade("Araraquara " + (i + first));
            endereco.setBairro(nomeAleatorio(10));
            endereco.setCep(14811060);
            endereco.setEstado("SP " + (i + first));
            endereco.setLogradouro("Alameda Paulista " + (i + first));
            listaEndereco.add(endereco);
        }
        return listaEndereco;
    }

    public static String nomeAleatorio(final int nCaracteres)
    {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nCaracteres; i++)
        {
            final int ch = rand.nextInt(letras.length);
            sb.append(letras[ch]);
        }
        return sb.toString();
    }

}
