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
@Table(name = "RL_PESSOA_EXERCICIO", schema = "GRANDEVIAFITNESS")
public class RlPessoaExercicio implements Entidade, Clonavel
{
   private static final long serialVersionUID = -4429591256978571518L;
   @Id
   @Column(name = "CO_SEQ_ATIVIDADE", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_PESSOA", referencedColumnName = "CO_SEQ_PESSOA", nullable = false)
   private Pessoa pessoa;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CO_TIPO_EXERC", referencedColumnName = "CO_SEQ_TIPO_EXERC", nullable = false)
   private TipoExercicio tipoExercicio;

   @Column(name = "DT_EXERCICIO_EFETUADO", nullable = false)
   private Date dataExercicio;

   @Column(name = "NU_REPETICOES", nullable = false, length = 11)
   private Integer numeroRepeticoes;

   @Column(name = "QT_PESO", nullable = false, length = 11)
   private BigDecimal quatidadePeso;

   @Column(name = "NU_SERIES", nullable = false, length = 11)
   private Integer numeroSeries;

   @Transient
   private String quantidadePesoFormatada;

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

   public TipoExercicio getTipoExercicio()
   {
      return tipoExercicio;
   }

   public void setTipoExercicio(final TipoExercicio tipoExercicio)
   {
      this.tipoExercicio = tipoExercicio;
   }

   public Date getDataExercicio()
   {
      return dataExercicio;
   }

   public void setDataExercicio(final Date dataExercicio)
   {
      this.dataExercicio = dataExercicio;
   }

   public Integer getNumeroRepeticoes()
   {
      return numeroRepeticoes;
   }

   public void setNumeroRepeticoes(final Integer numeroRepeticoes)
   {
      this.numeroRepeticoes = numeroRepeticoes;
   }

   public BigDecimal getQuatidadePeso()
   {
      return quatidadePeso;
   }

   public void setQuatidadePeso(final BigDecimal quatidadePeso)
   {
      this.quatidadePeso = quatidadePeso;
   }


   public String getQuantidadePesoFormatada()
   {
      if (getQuatidadePeso() != null)
      {
         try
         {
            quantidadePesoFormatada = Util.formateKilo(getQuatidadePeso());
         }
         catch (final Exception e)
         {
            // TODO: handle exception
         }
      }
      return quantidadePesoFormatada;
   }

   public void setQuantidadePesoFormatada(final String quantidadePesoFormatada)
   {
      this.quantidadePesoFormatada = quantidadePesoFormatada;
   }

   public Integer getNumeroSeries()
   {
      return numeroSeries;
   }

   public void setNumeroSeries(final Integer numeroSeries)
   {
      this.numeroSeries = numeroSeries;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   @Override
   public RlPessoaExercicio getClone()
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
