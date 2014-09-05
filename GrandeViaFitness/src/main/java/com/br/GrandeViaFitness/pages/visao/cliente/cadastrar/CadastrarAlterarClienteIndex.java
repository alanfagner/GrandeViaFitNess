package com.br.GrandeViaFitness.pages.visao.cliente.cadastrar;

import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class CadastrarAlterarClienteIndex extends BasePage
{

   private static final long serialVersionUID = 976377157001414346L;

   public CadastrarAlterarClienteIndex()
   {
      final Pessoa pessoa = new Pessoa();
      pessoa.setEndereco(new Endereco());
      criaFormulario(pessoa);
   }

   public CadastrarAlterarClienteIndex(final Pessoa pessoa)
   {
      criaFormulario(pessoa);
   }

   private void criaFormulario(final Pessoa pessoa)
   {
      add(new CadastrarAlterarClienteFrom("formulario", pessoa, feedback));
   }

}
