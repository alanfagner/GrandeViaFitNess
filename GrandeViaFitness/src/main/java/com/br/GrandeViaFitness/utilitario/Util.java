package com.br.GrandeViaFitness.utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util
{
   public static String retirarMascara(final String texto)
   {
      return texto.replaceAll("\\D", "");
   }

   public static String getDataPorExtenso(final Date data)
   {
      final DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy '-' HH:mm");
      return dfmt.format(data);
   }
}
