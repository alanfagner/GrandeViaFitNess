package com.br.GrandeViaFitness.dao;

import com.br.GrandeViaFitness.model.Authority;

public interface AuthorityDao
{
   public Authority findAuthority(final String authority);

   public void save(Authority authority);
}
