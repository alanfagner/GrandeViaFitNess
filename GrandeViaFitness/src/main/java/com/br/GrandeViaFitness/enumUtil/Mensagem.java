package com.br.GrandeViaFitness.enumUtil;

public enum Mensagem
{
   M01("{0} cadastrado com sucesso."),
   M02("{0} excluído com sucesso."),
   M03("{0} alterado com sucesso."),
   M04("O campo {0} é obrigatório!"),
   M05("Selecione uma opção NOVO, ALTERAR ou EXCLUIR)"),
   M06("É necessário selecionar um {0} para excluir!"),
   M07("É necessário selecionar um {0} para alterar!"),
   M08("É necessário limpar campos do {0} para cadastrar um novo."),
   M10("Devem ser anexado 2 fotos para criar o exercício."),
   M011("Não existem itens na lista de compras!"),
   M012("É necessário informar um cliente para {0}!"),
   M013("Venda realizada com sucesso!"),
   M014("Mensalidade paga com sucesso!"),
   M015("Já existe um pagamento referente a esse mês, ano e cliente! "),
   M016("Data {0} Invalida!"),
   M017("A {0} deve ser menor que a {1}!"),
   M018("Já existe um usuário cadastrado com esse CPF!"),
   M019("{0} não pode ser excluído, existe histórico associado!"),
   M020("Atividade Registrada com sucesso"),
   M021("Data da {0} invalida!");

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
