package com.br.GrandeViaFitness.pages.visao.produto.cadastrar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Produto;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class CadastrarAlterarProdutoIndex extends BasePage
{
   private static final long serialVersionUID = -8894163434001132781L;

   public CadastrarAlterarProdutoIndex()
   {
      criarForm(new Produto());
   }

   public CadastrarAlterarProdutoIndex(final Produto produto)
   {
      criarForm(produto);
   }

   private void criarForm(final Produto produto)
   {
      addOrReplace(new CadastrarAlterarProdutoForm(BasePage.FORMULARIO, produto));
   }
}
