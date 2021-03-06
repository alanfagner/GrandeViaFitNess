package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@Table(name = "RL_PRODUTO_VENDA", schema = "GRANDEVIAFITNESS")
public class RlProdutoVenda implements Entidade
{
   private static final long serialVersionUID = 5967466445663722783L;

   @Id
   @Column(name = "CO_SEQ_PRODUTO_VENDA", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_VENDA", referencedColumnName = "CO_SEQ_VENDA", nullable = false)
   private Venda venda;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PRODUTO", referencedColumnName = "CO_SEQ_PRODUTO", nullable = false)
   private Produto produto;

   @Column(name = "QT_PRODUTO", nullable = false)
   private Integer quantidadeVendido;

   @Transient
   private String valorTotal;

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   public Long getCodigo()
   {
      return codigo;
   }

   public Venda getVenda()
   {
      return venda;
   }

   public void setVenda(final Venda venda)
   {
      this.venda = venda;
   }

   public Produto getProduto()
   {
      return produto;
   }

   public void setProduto(final Produto produto)
   {
      this.produto = produto;
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

   public String getValorTotal()
   {
      if (produto != null && quantidadeVendido != null && produto.getCodigo() != null)
      {
         valorTotal =
            Util.priceWithDecimal(produto.getValorProduto().multiply(new BigDecimal(quantidadeVendido).setScale(2, RoundingMode.HALF_UP))
               .setScale(2, RoundingMode.HALF_UP));
      }
      return valorTotal;
   }

   public void setValorTotal(final String valorTotal)
   {
      this.valorTotal = valorTotal;
   }
}
