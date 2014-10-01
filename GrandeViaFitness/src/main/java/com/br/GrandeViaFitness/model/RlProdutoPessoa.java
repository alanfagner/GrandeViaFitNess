package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "RL_PRODUTO_PESSOA", schema = "GRANDEVIAFITNESS")
public class RlProdutoPessoa implements Entidade
{
   private static final long serialVersionUID = 5967466445663722783L;

   @Id
   @Column(name = "CO_SEQ_VENDA", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PESSOA", referencedColumnName = "CO_SEQ_PESSOA", nullable = false)
   private Pessoa pessoa;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PRODUTO", referencedColumnName = "CO_SEQ_PRODUTO", nullable = false)
   private Produto produto;

   @Column(name = "NU_VALOR_VENDA", nullable = false)
   private BigDecimal valorVenda;

   @Column(name = "DT_VENDA_PRODUTO", nullable = false)
   private Date dataVenda;

   @Column(name = "QT_PRODUTO", nullable = false)
   private Integer quantidadeVendido;


   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   public Long getCodigo()
   {
      return codigo;
   }

   public Pessoa getPessoa()
   {
      return pessoa;
   }

   public void setPessoa(final Pessoa pessoa)
   {
      this.pessoa = pessoa;
   }

   public Produto getProduto()
   {
      return produto;
   }

   public void setProduto(final Produto produto)
   {
      this.produto = produto;
   }

   public BigDecimal getValorVenda()
   {
      return valorVenda;
   }

   public void setValorVenda(final BigDecimal valorVenda)
   {
      this.valorVenda = valorVenda;
   }

   public Date getDataVenda()
   {
      return dataVenda;
   }

   public void setDataVenda(final Date dataVenda)
   {
      this.dataVenda = dataVenda;
   }

   public Integer getQuantidadeVendido()
   {
      return quantidadeVendido;
   }

   public void setQuantidadeVendido(final Integer quantidadeVendido)
   {
      this.quantidadeVendido = quantidadeVendido;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }
}
