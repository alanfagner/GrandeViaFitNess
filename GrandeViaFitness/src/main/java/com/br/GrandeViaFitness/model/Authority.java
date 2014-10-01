package com.br.GrandeViaFitness.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "TB_PERMISSAO", schema = "GRANDEVIAFITNESS")
public class Authority implements GrantedAuthority
{
   private static final long serialVersionUID = -1131390867997733051L;

   @Id
   @Column(name = "CO_SEQ_PERMISSAO", nullable = false)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long codigo;

   @Column(name = "SG_PERMISSAO", nullable = false, length = 5)
   private String authority;

   public long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final long codigo)
   {
      this.codigo = codigo;
   }

   public Authority()
   {
   }

   public Authority(final String authority)
   {
      this.authority = authority;
   }

   @Override
   public String getAuthority()
   {
      return authority;
   }

   public void setAuthority(final String authority)
   {
      this.authority = authority;
   }

}
