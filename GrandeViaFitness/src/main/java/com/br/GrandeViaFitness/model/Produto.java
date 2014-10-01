package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO", schema = "GRANDEVIAFITNESS")
public class Produto implements Entidade
{

   private static final long serialVersionUID = 7664999582520513025L;
   @Id
   @Column(name = "CO_SEQ_PRODUTO", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @Column(name = "NO_PRODUTO", nullable = false, length = 100)
   private String nomeProduto;

   @Column(name = "NU_VALOR_PRODUTO", nullable = false)
   private BigDecimal valorProduto;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public String getNomeProduto()
   {
      return nomeProduto;
   }

   public void setNomeProduto(final String nomeProduto)
   {
      this.nomeProduto = nomeProduto;
   }

   public BigDecimal getValorProduto()
   {
      return valorProduto;
   }

   public void setValorProduto(final BigDecimal valorProduto)
   {
      this.valorProduto = valorProduto;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
