package com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio;

import java.util.Date;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.RlPessoaExercicio;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

@AuthorizeInstantiation("R_ADM")
public class MobileExecutarExercicioIndex extends BasePageMobile
{
   public MobileExecutarExercicioIndex(final TipoExercicio tipoExercicio, final Date dataCadastro, final Pessoa usuarioAtividade)
   {
      final RlPessoaExercicio auxPessoaExercicio = new RlPessoaExercicio();
      auxPessoaExercicio.setTipoExercicio(tipoExercicio);
      add(new MobileExecutarExercicioFrom(BasePage.FORMULARIO, auxPessoaExercicio, dataCadastro, usuarioAtividade));
   }

   private static final long serialVersionUID = 2588451429792268189L;

   public MobileExecutarExercicioIndex()
   {
      setResponsePage(new MobileHomeIndex());
   }
}
