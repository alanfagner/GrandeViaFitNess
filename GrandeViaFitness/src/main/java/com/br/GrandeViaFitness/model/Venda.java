package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "TB_VENDA", schema = "GRANDEVIAFITNESS")
public class Venda  implements Entidade
{
   private static final long serialVersionUID = -5837586667431132455L;

   @Id
   @Column(name = "CO_SEQ_VENDA", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PESSOA", referencedColumnName = "CO_SEQ_PESSOA", nullable = false)
   private Pessoa pessoa;

   @Column(name = "VL_TOTAL_VENDA", nullable = false)
   private BigDecimal valorTotal;

   @Column(name = "DT_VENDA", nullable = false)
   private Date dataVenda;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public Pessoa getPessoa()
   {
      return pessoa;
   }

   public void setPessoa(final Pessoa pessoa)
   {
      this.pessoa = pessoa;
   }

   public BigDecimal getValorTotal()
   {
      return valorTotal;
   }

   public void setValorTotal(final BigDecimal valorTotal)
   {
      this.valorTotal = valorTotal;
   }

   public Date getDataVenda()
   {
      return dataVenda;
   }

   public void setDataVenda(final Date dataVenda)
   {
      this.dataVenda = dataVenda;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }
}
