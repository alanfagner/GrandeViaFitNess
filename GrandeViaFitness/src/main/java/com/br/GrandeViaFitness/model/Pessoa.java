package com.br.GrandeViaFitness.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.br.GrandeViaFitness.enumUtil.PermissaoEnum;
import com.br.GrandeViaFitness.enumUtil.SexoEnum;
import com.br.GrandeViaFitness.utilitario.Util;


@Entity
@Table(name = "TB_PESSOA", schema = "GRANDEVIAFITNESS")
public class Pessoa implements Entidade, Cliente, Funcionario, Instrutor
{
   private static final long serialVersionUID = 2633297273367116608L;

   @Id
   @Column(name = "CO_SEQ_PESSOA", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "SEQ_PERMISSAO", referencedColumnName = "CO_SEQ_PERMISSAO", nullable = false)
   private Authority authority;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "SEQ_ENDERECO", referencedColumnName = "CO_SEQ_ENDERECO", nullable = false)
   private Endereco endereco;

   @Column(name = "NO_PESSOA", nullable = false, length = 100)
   private String nomePessoa;

   @Column(name = "NU_CPF_PESSOA", unique = true, nullable = false, length = 11)
   private String cpfPessoa;

   @Column(name = "DS_EMAIL", nullable = false, unique = true, length = 100)
   private String emailPessoa;

   @Column(name = "DT_NASCIMENTO", nullable = false)
   private Date dataNascimentoPessoa;

   @Column(name = "NU_RESIDENCIAL", nullable = false)
   private Integer numeroResidencial;

   @Column(name = "DS_SENHA", nullable = false, length = 45)
   private String senhaPessoa;

   @Column(name = "NU_TEL_CELULAR", nullable = false, length = 45)
   private String numeroCelulaPessoa;

   @Column(name = "DS_SEXO", nullable = false, length = 1)
   @Enumerated(EnumType.STRING)
   private SexoEnum sexo;

   @Column(name = "TP_CARGO", nullable = false, length = 1)
   @Enumerated(EnumType.ORDINAL)
   private PermissaoEnum cargoEnum;

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

   public Authority getAuthority()
   {
      return authority;
   }

   public void setAuthority(final Authority authority)
   {
      this.authority = authority;
   }

   public Endereco getEndereco()
   {
      return endereco;
   }

   public void setEndereco(final Endereco endereco)
   {
      this.endereco = endereco;
   }

   public String getNomePessoa()
   {
      return nomePessoa;
   }

   public void setNomePessoa(final String nomePessoa)
   {
      this.nomePessoa = nomePessoa;
   }

   public String getCpfPessoa()
   {
      return cpfPessoa;
   }

   public void setCpfPessoa(final String cpfPessoa)
   {
      this.cpfPessoa = cpfPessoa == null ? null : Util.retirarMascara(cpfPessoa);
   }

   public String getEmailPessoa()
   {
      return emailPessoa;
   }

   public void setEmailPessoa(final String emailPessoa)
   {
      this.emailPessoa = emailPessoa;
   }

   public Date getDataNascimentoPessoa()
   {
      return dataNascimentoPessoa;
   }

   public void setDataNascimentoPessoa(final Date dataNascimentoPessoa)
   {
      this.dataNascimentoPessoa = dataNascimentoPessoa;
   }

   public Integer getNumeroResidencial()
   {
      return numeroResidencial;
   }

   public void setNumeroResidencial(final Integer numeroResidencial)
   {
      this.numeroResidencial = numeroResidencial;
   }

   public String getSenhaPessoa()
   {
      return senhaPessoa;
   }

   public void setSenhaPessoa(final String senhaPessoa)
   {
      this.senhaPessoa = senhaPessoa;
   }

   public String getNumeroCelulaPessoa()
   {
      return numeroCelulaPessoa;
   }

   public void setNumeroCelulaPessoa(final String numeroCelulaPessoa)
   {
      this.numeroCelulaPessoa = numeroCelulaPessoa == null ? null : Util.retirarMascara(numeroCelulaPessoa);
   }

   public SexoEnum getSexo()
   {
      return sexo;
   }

   public void setSexo(final SexoEnum sexo)
   {
      this.sexo = sexo;
   }

   public PermissaoEnum getCargoEnum()
   {
      return cargoEnum;
   }

   public void setCargoEnum(final PermissaoEnum cargoEnum)
   {
      this.cargoEnum = cargoEnum;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
