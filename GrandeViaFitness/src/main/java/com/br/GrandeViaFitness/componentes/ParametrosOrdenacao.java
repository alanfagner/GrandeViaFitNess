package com.br.GrandeViaFitness.componentes;

import java.io.Serializable;

public class ParametrosOrdenacao implements Serializable
{
   private static final long serialVersionUID = -1029358380298169334L;
   private String coluna;
   private String ordernar;

   public ParametrosOrdenacao(final String Coluna, final Boolean isAscending)
   {
      coluna = Coluna;
      ordernar = isAscending ? "ASC" : "DESC";
   }

   public String getColuna()
   {
      return coluna;
   }

   public void setColuna(final String coluna)
   {
      this.coluna = coluna;
   }

   public String getOrdernar()
   {
      return ordernar;
   }

   public void setOrdernar(final String ordernar)
   {
      this.ordernar = ordernar;
   }

}
