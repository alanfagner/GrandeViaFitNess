package com.br.GrandeViaFitness.utilitario;

import java.io.Serializable;

public class Paginacao implements Serializable
{
   private static final long serialVersionUID = -2591041822148041507L;

   private Integer posicao;
   private Integer limite;

   public Paginacao()
   {
   }

   public Paginacao(final long posicao, final long limite)
   {
      this.posicao = (int) posicao;
      this.limite = (int) limite;
   }

   public Integer getLimite()
   {
      return limite;
   }

   public Integer getPosicao()
   {
      return posicao;
   }

   public void setLimite(final Integer limite)
   {
      this.limite = limite;
   }

   public void setPosicao(final Integer posicao)
   {
      this.posicao = posicao;
   }
}
