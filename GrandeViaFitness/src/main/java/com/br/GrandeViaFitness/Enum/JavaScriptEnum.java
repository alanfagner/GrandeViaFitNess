package com.br.GrandeViaFitness.Enum;

public enum JavaScriptEnum
{

   JQUERYTHEMA(1, "jqueryThema.js");
   private Integer codigo;
   private String descricao;

   private JavaScriptEnum(final Integer codigo, final String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   public Integer getCodigo()
   {
      return codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }
}
