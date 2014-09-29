package com.br.GrandeViaFitness.Dao;

import java.util.List;
import com.br.GrandeViaFitness.Model.Authority;
import com.br.GrandeViaFitness.Model.User;

public interface UserDao
{
   public User findByUser(final String cpf);

   public Authority findAuthority(final String authority);

   public List<User> findAll();

   public boolean save(final Authority authority);
}
