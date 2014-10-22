package com.br.GrandeViaFitness.utilitario;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util
{
   public static String retirarMascara(final String texto)
   {
      return texto.replaceAll("\\D", "");
   }

   public static boolean isStringNaoVazia(final String s)
   {
      return StringUtils.isNotBlank(s);
   }

   public static String formataData(final Date data, final String formato)
   {
      String retorno = "";
      if (data != null)
      {
         retorno = new DateTime(data).toString(formato);
      }
      return retorno;
   }

   public static Date converteData(final Object valor)
   {
      Date data = null;
      if (valor != null)
      {
         if (String.class.equals(valor.getClass()))
         {
            if (Util.isStringNaoVazia((String) valor))
            {
               final DateTimeFormatter fmt;
               if (((String) valor).indexOf("-") == 4)
               {
                  fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
               }
               else
               {
                  fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
               }

               final LocalDate date = LocalDate.parse((String) valor, fmt);
               data = date.toDate();
            }
         }
         else if (Date.class.equals(valor.getClass()))
         {
            data = (Date) valor;
         }
         else if (Timestamp.class.equals(valor.getClass()))
         {
            final Timestamp t = (Timestamp) valor;
            data = new Date(t.getTime());
         }
      }
      return data;
   }

   public static String getDataPorExtenso(final Date data)
   {
      final DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy '-' HH:mm");
      return dfmt.format(data);
   }
}
