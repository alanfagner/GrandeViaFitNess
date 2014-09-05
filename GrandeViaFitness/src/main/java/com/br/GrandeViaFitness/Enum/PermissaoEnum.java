package com.br.GrandeViaFitness.Enum;

public enum PermissaoEnum
{
   CLIENTE("R_CLI", "Cliente"), FUNCIONARIO("R_ADM", "Funcionario");

   private String sigla;
   private String descricao;

   private PermissaoEnum(final String sigla, final String descricao)
   {
      this.sigla = sigla;
      this.descricao = descricao;
   }
   public String getSigla()
   {
      return sigla;
   }

   public String getDescricao()
   {
      return descricao;
   }

}
