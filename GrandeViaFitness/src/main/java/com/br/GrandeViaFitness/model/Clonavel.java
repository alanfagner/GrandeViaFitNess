package com.br.GrandeViaFitness.model;

import java.io.Serializable;

public interface Clonavel extends Cloneable, Serializable
{
   public Serializable getClone();
}
