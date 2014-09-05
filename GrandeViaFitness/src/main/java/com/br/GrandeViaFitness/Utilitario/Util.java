package com.br.GrandeViaFitness.Utilitario;

public class Util
{
   public static String retirarMascara(final String texto)
   {
      return texto.replaceAll("\\D", "");
   }
}
