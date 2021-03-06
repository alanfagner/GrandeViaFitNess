package com.br.GrandeViaFitness.pages.visao.cliente.visualizar;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

@AuthorizeInstantiation("R_ADM")
public class VisualizarClienteIndex extends BasePage
{
   private static final long serialVersionUID = -2470299978319694581L;
   @SpringBean
   private PessoaAS pessoaAS;

   public VisualizarClienteIndex(Pessoa pessoa)
   {
      pessoa = pessoaAS.buscaCompleta(pessoa);
      add(new VisualizarClienteForm(BasePage.FORMULARIO, pessoa));
   }

}
