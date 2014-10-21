package com.br.GrandeViaFitness.pages.visao.mobile.BasePageMobile;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.context.SecurityContextHolder;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.utilitario.BaseUtil;

public class BasePageMobile extends WebPage
{
   private static final long serialVersionUID = 159928889691659084L;
   private Pessoa usuarioLogado;
   @SpringBean
   private PessoaAS pessoaAS;

   @Override
   protected void onBeforeRender()
   {
      // TODO Auto-generated method stub
      super.onBeforeRender();
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      BaseUtil.geralMobile(response, false);

   }

   public Pessoa getUsuarioLogado()
   {
      if (usuarioLogado == null)
      {
         usuarioLogado = new Pessoa();
         if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
         {
            usuarioLogado = pessoaAS.buscaPessoaPorCpf(SecurityContextHolder.getContext().getAuthentication().getName());
         }
      }
      return usuarioLogado;
   }

}
