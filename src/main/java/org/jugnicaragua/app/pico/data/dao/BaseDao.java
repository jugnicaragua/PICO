package org.jugnicaragua.app.pico.data.dao;

import java.util.List;
import org.hibernate.Session;

public interface BaseDao<T, ID extends Object> {

  String getTableName();

  Session getSession();

  T get(ID id);

  List<T> getAll();

  void save(T entity);

  void saveAll(List<T> entities);

  void remove(T entity);

  void removeAll();
}
