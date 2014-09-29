package com.br.GrandeViaFitness.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.br.GrandeViaFitness.Utilitario.Util;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Entidade
{
   private static final long serialVersionUID = 9171062555429369830L;

   @Id
   @Column(name = "CO_SEQ_ENDERECO", nullable = false, length = 3)
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long codigo;

   @Column(name = "NO_LOGRADOURO", nullable = false, length = 100)
   private String logradouro;

   @Column(name = "NO_BAIRRO", nullable = false, length = 100)
   private String bairro;

   @Column(name = "NU_CEP", nullable = false, length = 8)
   private String cep;

   @Column(name = "NO_CIDADE", nullable = false, length = 45)
   private String cidade;

   @Column(name = "SG_ESTADO", nullable = false, length = 2)
   private String estado;

   public long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final long codigo)
   {
      this.codigo = codigo;
   }

   public String getLogradouro()
   {
      return logradouro;
   }

   public void setLogradouro(final String logradouro)
   {
      this.logradouro = logradouro;
   }

   public String getBairro()
   {
      return bairro;
   }

   public void setBairro(final String bairro)
   {
      this.bairro = bairro;
   }

   public String getCep()
   {
      return cep;
   }

   public void setCep(final String cep)
   {
      this.cep = Util.retirarMascara(cep);
   }

   public String getCidade()
   {
      return cidade;
   }

   public void setCidade(final String cidade)
   {
      this.cidade = cidade;
   }

   public String getEstado()
   {
      return estado;
   }

   public void setEstado(final String estado)
   {
      this.estado = estado;
   }

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

}
