package com.br.GrandeViaFitness.enumUtil;

public enum PermissaoEnum
{
   CLIENTE(0, "R_ADM", "Cliente"), INSTRUTOR(1, "R_ADM", "Instrutor"), ADMINISTRADOR(2, "R_ADM", "Funcionário");

   private Integer codigo;
   private String sigla;
   private String descricao;

   private PermissaoEnum(final Integer codigo, final String sigla, final String descricao)
   {
      this.codigo = codigo;
      this.sigla = sigla;
      this.descricao = descricao;
   }

   public Integer getCodigo()
   {
      return codigo;
   }

   public String getSigla()
   {
      return sigla;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public PermissaoEnum recuperaCargoPorSigla(final String sigla)
   {
      for (final PermissaoEnum auxPermissao : PermissaoEnum.values())
      {
         if (auxPermissao.getSigla().endsWith(sigla))
         {
            return auxPermissao;
         }
      }
      return null;
   }

}
