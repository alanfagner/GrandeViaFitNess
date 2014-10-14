package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CORPO", schema = "GRANDEVIAFITNESS")
public class Corpo implements Entidade, Clonavel
{
   private static final long serialVersionUID = -6955624083137952631L;

   @Id
   @Column(name = "CO_SEQ_CORPO", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @Column(name = "NO_MEMBRO_CORPO", nullable = false, unique = true, length = 45)
   private String nomeMembroCorpo;

   @Column(name = "DS_MEMBRO_CORPO", nullable = false, unique = true, length = 100)
   private String descricaoMembroCorpo;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public String getNomeMembroCorpo()
   {
      return nomeMembroCorpo;
   }

   public void setNomeMembroCorpo(final String nomeMembroCorpo)
   {
      this.nomeMembroCorpo = nomeMembroCorpo;
   }

   public String getDescricaoMembroCorpo()
   {
      return descricaoMembroCorpo;
   }

   public void setDescricaoMembroCorpo(final String descricaoMembroCorpo)
   {
      this.descricaoMembroCorpo = descricaoMembroCorpo;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   @Override
   public Corpo getClone()
   {
      try
      {
         return getClass().cast(super.clone());
      }
      catch (final CloneNotSupportedException e)
      {
         e.printStackTrace();
      }
      return this;
   }
}
