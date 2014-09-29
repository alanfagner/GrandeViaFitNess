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
@Table(name = "TB_TIPO_EXERCICIO", schema = "GRANDEVIAFITNESS")
public class TipoExercicio implements Entidade
{
   private static final long serialVersionUID = 2916816737898751364L;
   @Id
   @Column(name = "CO_SEQ_TIPO_EXERC", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_TIPO_EQUIP", referencedColumnName = "CO_SEQ_TIPO_EQUIP", nullable = false)
   private TipoEquipamento tipoEquipamento;

   @Column(name = "NO_EXERCICIO", nullable = false, length = 45)
   private String nomeExercicio;

   @Column(name = "DS_EXERCICIO", nullable = false, length = 100)
   private String descricaoExercicio;


   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public TipoEquipamento getTipoEquipamento()
   {
      return tipoEquipamento;
   }

   public void setTipoEquipamento(final TipoEquipamento tipoEquipamento)
   {
      this.tipoEquipamento = tipoEquipamento;
   }

   public String getNomeExercicio()
   {
      return nomeExercicio;
   }

   public void setNomeExercicio(final String nomeExercicio)
   {
      this.nomeExercicio = nomeExercicio;
   }

   public String getDescricaoExercicio()
   {
      return descricaoExercicio;
   }

   public void setDescricaoExercicio(final String descricaoExercicio)
   {
      this.descricaoExercicio = descricaoExercicio;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }
}
