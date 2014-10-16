package com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import com.br.GrandeViaFitness.utilitario.BaseUtil;

public class BasePageMobile extends WebPage
{
   private static final long serialVersionUID = 159928889691659084L;

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      BaseUtil.geralMobile(response, false);
   }
}
