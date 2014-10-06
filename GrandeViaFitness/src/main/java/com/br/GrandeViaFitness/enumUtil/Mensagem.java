package com.br.GrandeViaFitness.enumUtil;

public enum Mensagem
{
   M01("Usuário cadastrado com sucesso."), M02("Usuário excluido com sucesso."), M03("O campo {0} é obrigatorio!"), M04(
      "Equipamento incluído com sucesso."), M05("Equipamento alterado com sucesso."), M06(
"Selecione uma opção NOVO, ALTERAR ou EXCLUIR)"), M07(
      "É necessario selecionar um equipamento para excluir!"), M08("É necessario selecionar um equipamento para alterar!");

   private String descricao;

   private Mensagem(final String descricao)
   {
      this.descricao = descricao;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public static String recuperaMensagem(final Mensagem mensagem, final String... listaMensagem)
   {
      String mensagemR = mensagem.getDescricao();
      for (Integer i = 0; i < listaMensagem.length; i++)
      {
         mensagemR = mensagemR.replace("{" + i + "}", listaMensagem[i]);
      }
      return mensagemR;
   }
}
