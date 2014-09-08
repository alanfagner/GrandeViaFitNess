package com.br.GrandeViaFitness.Enum;

public enum Mensagem
{
   M01("Usuário cadastrado com sucesso.");

   private String descricao;

   private Mensagem(final String descricao)
   {
      this.descricao = descricao;
   }
   public String getDescricao()
   {
      return descricao;
   }
}
