package com.br.GrandeViaFitness.pages.visao.cliente.cadastrar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Endereco;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
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
      add(new CadastrarAlterarClienteForm("formulario", pessoa, feedback));
   }

}
