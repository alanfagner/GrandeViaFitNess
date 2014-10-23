package com.br.GrandeViaFitness.pages.visao.mobile.MobileDetalharExercicio;

import java.util.Date;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

@AuthorizeInstantiation("R_ADM")
public class MobileDetalharExercicioIndex extends BasePageMobile
{
   private static final long serialVersionUID = 4571970844421198398L;

   public MobileDetalharExercicioIndex(final TipoExercicio tipoExercicio, final Date dataCadastro, final Pessoa usuarioAtividade)
   {
      addOrReplace(new MobileDetalharExercicioForm(BasePage.FORMULARIO, tipoExercicio, dataCadastro, usuarioAtividade));
   }

   public MobileDetalharExercicioIndex()
   {
      setResponsePage(new MobileHomeIndex());
   }
}
