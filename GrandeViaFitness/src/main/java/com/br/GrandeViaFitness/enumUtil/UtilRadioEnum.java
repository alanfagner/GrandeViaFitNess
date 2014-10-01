package com.br.GrandeViaFitness.enumUtil;

public enum UtilRadioEnum
{
   ALTERAR(1, "Alterar"), EXCLUIR(2, "Excluir"), NOVO(2, "Novo");

   private Integer codigo;
   private String descricao;

   private UtilRadioEnum(final Integer codigo, final String descricao)
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
