package com.br.GrandeViaFitness.dao;

import java.util.List;
import com.br.GrandeViaFitness.model.Authority;
import com.br.GrandeViaFitness.model.User;

public interface UserDao
{
   public User findByUser(final String cpf);

   public Authority findAuthority(final String authority);

   public List<User> findAll();

   public boolean save(final Authority authority);
}
