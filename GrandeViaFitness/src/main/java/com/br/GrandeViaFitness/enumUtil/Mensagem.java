package com.br.GrandeViaFitness.enumUtil;

public enum Mensagem
{
   M01("Usuário cadastrado com sucesso."), M02("Usuário excluido com sucesso.");

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
