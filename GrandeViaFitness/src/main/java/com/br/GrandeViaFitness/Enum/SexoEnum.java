package com.br.GrandeViaFitness.Enum;

public enum SexoEnum
{
   M("M", "Masculino"), F("F", "Feminino");

   private String codigo;
   private String descricao;

   private SexoEnum(final String codigo, final String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   public String getCodigo()
   {
      return codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }

}
