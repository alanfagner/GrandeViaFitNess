package com.br.GrandeViaFitness.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RL_CORPO_EQUIPAMENTO", schema = "GRANDEVIAFITNESS")
public class RlCorpoEquipamento implements Entidade
{
   private static final long serialVersionUID = 4918002112772403000L;

   @Id
   @Column(name = "CO_SEQ_EQUIPAMENTO", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_CORPO", referencedColumnName = "CO_SEQ_CORPO", nullable = false)
   private Corpo corpo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_TIPO_EQUIP", referencedColumnName = "CO_SEQ_TIPO_EQUIP", nullable = false)
   private TipoEquipamento tipoEquipamento;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public Corpo getCorpo()
   {
      return corpo;
   }

   public void setCorpo(final Corpo corpo)
   {
      this.corpo = corpo;
   }

   public TipoEquipamento getTipoEquipamento()
   {
      return tipoEquipamento;
   }

   public void setTipoEquipamento(final TipoEquipamento tipoEquipamento)
   {
      this.tipoEquipamento = tipoEquipamento;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
