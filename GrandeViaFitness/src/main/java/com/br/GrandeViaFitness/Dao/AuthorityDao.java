package com.br.GrandeViaFitness.Dao;

import com.br.GrandeViaFitness.Model.Authority;

public interface AuthorityDao
{
   public Authority findAuthority(final String authority);

   public void save(Authority authority);
}
