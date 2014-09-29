package com.br.GrandeViaFitness.Dao.Generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.br.GrandeViaFitness.Model.Entidade;

public interface GVFDao<E extends Entidade> extends Dao<E>
{

   public E obter(final Serializable id);

   public List<E> consultar(final E entidade);

   public List<E> listar();

   Object consultarUnicoRegistro(final String hql, final Map<String, Object> parametros);

   public void incluir(final E entidade);

   public void alterar(final E entidade);

   public void excluir(final E entidade);

   public void excluirTodos(final List<E> entidades);

}
