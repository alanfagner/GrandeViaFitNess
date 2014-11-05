package com.br.GrandeViaFitness.enumUtil;

public enum MesReferenciaEnum
{
   JAN(0, "Janeiro"),
   FEV(1, "Fevereiro"),
   MAR(2, "Março"),
   ABR(3, "Abril"),
   MAI(4, "Maio"),
   JUN(5, "Junho"),
   JUL(6, "Julho"),
   AGO(7, "Agosto"),
   SET(8, "Setembro"),
   OUT(9, "Outubro"),
   NOV(10, "Novembro"),
   DEZ(11, "Dezembro");

   private MesReferenciaEnum(final Integer codigo, final String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   private Integer codigo;
   private String descricao;

   public Integer getCodigo()
   {
      return codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }
}
