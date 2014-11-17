package com.br.GrandeViaFitness.utilitario;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util
{
   private static final Locale BRAZIL = new Locale("pt", "BR");
   private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(Util.BRAZIL);

   public static String formatString(final String string, final String mask) throws java.text.ParseException
   {
      final javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter(mask);
      mf.setValueContainsLiteralCharacters(false);
      return mf.valueToString(string);
   }

   public static String priceWithDecimal(final BigDecimal price)
   {
      final DecimalFormat formatter = new DecimalFormat("¤ ###,###,##0.00", Util.REAL);
      return formatter.format(price);
   }

   public static String retirarMascara(final String texto)
   {
      if (texto != null)
      {
         return texto.replaceAll("\\D", "");
      }
      return null;
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
      final DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
      return dfmt.format(data);
   }
}
