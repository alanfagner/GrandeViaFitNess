package com.br.GrandeViaFitness.vo;

import java.io.Serializable;
import com.br.GrandeViaFitness.model.Entidade;

public class AparelhosVO implements Entidade
{
   private static final long serialVersionUID = 1932842660669756000L;
   private Long codigo;
   private String nomeAparelho;
   private String parteCorpo;

   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   public Long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(final Long codigo)
   {
      this.codigo = codigo;
   }

}
