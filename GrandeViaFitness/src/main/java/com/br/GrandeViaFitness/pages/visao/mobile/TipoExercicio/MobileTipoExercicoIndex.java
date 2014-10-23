package com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio;

import java.util.Date;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import com.br.GrandeViaFitness.model.Corpo;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;
import com.br.GrandeViaFitness.pages.visao.mobile.MobileHomeIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile.BasePageMobile;

@AuthorizeInstantiation("R_ADM")
public class MobileTipoExercicoIndex extends BasePageMobile
{
   private static final long serialVersionUID = 3614663156458644305L;

   public MobileTipoExercicoIndex(final Corpo corpo, final Date dataCadastro, final Pessoa usuarioAtividade)
   {
      addOrReplace(new MobileTipoExercicoForm(BasePage.FORMULARIO, corpo, dataCadastro, usuarioAtividade));
   }

   public MobileTipoExercicoIndex()
   {
      setResponsePage(new MobileHomeIndex());
   }
}
