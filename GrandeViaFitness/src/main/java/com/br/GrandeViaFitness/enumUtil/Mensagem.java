package com.br.GrandeViaFitness.enumUtil;

public enum Mensagem
{
   M01("{0} cadastrado com sucesso."),
   M02("{0} excluido com sucesso."),
   M03("{0} alterado com sucesso."),
   M04("O campo {0} é obrigatorio!"),
   M05("Selecione uma opção NOVO, ALTERAR ou EXCLUIR)"),
   M06("É necessario selecionar um {0} para excluir!"),
   M07("É necessario selecionar um {0} para alterar!"),
   M08("É necessarioa limpar campos do {0} para cadastrar um novo.");
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
