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
import javax.persistence.Transient;
import com.br.GrandeViaFitness.utilitario.Util;

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

   @Transient
   private String dataFormatada;

   @Transient
   private String valorFormatado;

   @Transient
   private Date dataFim;

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

   public String getDataFormatada()
   {
      if (getDataVenda() != null)
      {
         dataFormatada = Util.formataData(getDataVenda(), "dd/MM/yyyy");
      }
      return dataFormatada;
   }

   public void setDataFormatada(final String dataFormatada)
   {
      this.dataFormatada = dataFormatada;
   }

   public String getValorFormatado()
   {
      if (getValorTotal() != null)
      {
         valorFormatado = Util.priceWithDecimal(getValorTotal());
      }
      return valorFormatado;
   }

   public void setValorFormatado(final String valorFormatado)
   {
      this.valorFormatado = valorFormatado;
   }

   public Date getDataFim()
   {
      return dataFim;
   }

   public void setDataFim(final Date dataFim)
   {
      this.dataFim = dataFim;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }
}
