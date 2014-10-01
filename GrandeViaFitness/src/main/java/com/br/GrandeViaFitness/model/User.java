package com.br.GrandeViaFitness.model;

import java.util.LinkedList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("usuarioSessao")
@Scope("session")
public class User implements UserDetails
{

   public static final long serialVersionUID = 1L;
   private Long id;
   private List<Authority> authorities;
   private String email;
   private String password;
   private String cpf;

   private boolean enabled;

   public User()
   {
   }

   public User(final String cpf)
   {
      this.cpf = cpf;
   }

   public User(final String cpf, final String password)
   {
      this(cpf, password, new String[]{});
   }

   public User(final String cpf, final String password, final String authority)
   {
      this(cpf, password, new String[]{authority});
   }

   public User(final String cpf, final String password, final String... authorities)
   {
      this.cpf = cpf;
      this.password = password;
      enabled = true;

      for (final String authority : authorities)
      {
         getAuthorities().add(new Authority(authority));
      }

   }

   @Override
   public List<Authority> getAuthorities()
   {
      if (authorities == null)
      {
         authorities = new LinkedList<Authority>();
      }

      return authorities;
   }

   @Override
   public String getPassword()
   {
      return password;
   }

   @Override
   public String getUsername()
   {
      return cpf;
   }

   @Override
   public boolean isAccountNonExpired()
   {
      return true;
   }

   @Override
   public boolean isAccountNonLocked()
   {
      return enabled;
   }

   @Override
   public boolean isCredentialsNonExpired()
   {
      return true;
   }

   @Override
   public boolean isEnabled()
   {
      return enabled;
   }

   public long getId()
   {
      return id;
   }

   public void setId(final long id)
   {
      this.id = id;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(final String email)
   {
      this.email = email;
   }

   public void setAuthorities(final List<Authority> authorities)
   {
      this.authorities = authorities;
   }

   public void setPassword(final String password)
   {
      this.password = password;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(final String cpf)
   {
      this.cpf = cpf;
   }
}
