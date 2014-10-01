package com.br.GrandeViaFitness.utilitario;

public class Util
{
   public static String retirarMascara(final String texto)
   {
      return texto.replaceAll("\\D", "");
   }
}
