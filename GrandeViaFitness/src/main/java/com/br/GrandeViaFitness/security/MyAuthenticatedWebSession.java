package com.br.GrandeViaFitness.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.br.GrandeViaFitness.Servico.PessoaServico;
import com.br.GrandeViaFitness.pages.login.basePage.BasePage;

public class MyAuthenticatedWebSession extends AuthenticatedWebSession
{

   private static final long serialVersionUID = 1L;
   private static final Logger logger = LoggerFactory.getLogger(MyAuthenticatedWebSession.class);

   @SpringBean(name = "authenticationManager")
   private AuthenticationManager authenticationManager;

   @SpringBean
   private PessoaServico pessoaServico;

   public MyAuthenticatedWebSession(final Request request)
   {
      super(request);
      injectDependencies();
      ensureDependenciesNotNull();
   }

   private void ensureDependenciesNotNull()
   {
      if (authenticationManager == null)
      {
         throw new IllegalStateException("AdminSession requires an authenticationManager.");
      }
   }

   private void injectDependencies()
   {
      Injector.get().inject(this);
   }

   @Override
   public boolean authenticate(final String userEmail, final String password)
   {

      try
      {
         final Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail,
               password));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         BasePage.setUsuarioLogado(pessoaServico.buscaPessoaPorEmail(userEmail));
         return authentication.isAuthenticated();
      }
      catch (final AuthenticationException e)
      {
         MyAuthenticatedWebSession.logger.warn(String.format("User '%s' failed to login. Reason: %s", e.getMessage(),
            e.getMessage()));
         return false;
      }

   }

   @Override
   public Roles getRoles()
   {
      final Roles roles = new Roles();
      getRolesIfSignedIn(roles);
      return roles;
   }

   private void getRolesIfSignedIn(final Roles roles)
   {
      if (isSignedIn())
      {
         final Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
         addRolesFromAuthentication(roles, authentication);
      }
   }

   private void addRolesFromAuthentication(final Roles roles, final Authentication authentication)
   {
      for (final GrantedAuthority authority : authentication.getAuthorities())
      {
         roles.add(authority.getAuthority());
      }
   }

}