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
@Table(name = "TB_MENSALIDADE")
public class Mensalidade implements Entidade
{
   private static final long serialVersionUID = 8213412052932486898L;
   @Id
   @Column(name = "CO_SEQ_MENSALIDADE", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PESSOA", referencedColumnName = "CO_SEQ_PESSOA", nullable = false)
   private Pessoa pessoa;

   @Column(name = "DT_PAGAMENTO", nullable = false, length = 100)
   private Date dataPagamento;

   @Column(name = "VL_PAGO", nullable = false, length = 100)
   private BigDecimal valorPago;

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

   public Date getDataPagamento()
   {
      return dataPagamento;
   }

   public void setDataPagamento(final Date dataPagamento)
   {
      this.dataPagamento = dataPagamento;
   }

   public BigDecimal getValorPago()
   {
      return valorPago;
   }

   public void setValorPago(final BigDecimal valorPago)
   {
      this.valorPago = valorPago;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
