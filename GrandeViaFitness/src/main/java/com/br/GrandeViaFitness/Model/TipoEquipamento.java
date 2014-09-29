package com.br.GrandeViaFitness.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TIPO_EQUIPAMENTO", schema = "GRANDEVIAFITNESS")
public class TipoEquipamento implements Entidade
{
   private static final long serialVersionUID = 7283684931613148092L;
   @Id
   @Column(name = "CO_SEQ_TIPO_EQUIP", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @Column(name = "NO_TIPO_EQUIP", nullable = false, unique = true, length = 45)
   private String nomeTipoEquip;

   @Column(name = "DS_TIPO_EQUIP", nullable = false, unique = true, length = 100)
   private String descricaoTipoEquip;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public String getNomeTipoEquip()
   {
      return nomeTipoEquip;
   }

   public void setNomeTipoEquip(final String nomeTipoEquip)
   {
      this.nomeTipoEquip = nomeTipoEquip;
   }

   public String getDescricaoTipoEquip()
   {
      return descricaoTipoEquip;
   }

   public void setDescricaoTipoEquip(final String descricaoTipoEquip)
   {
      this.descricaoTipoEquip = descricaoTipoEquip;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
