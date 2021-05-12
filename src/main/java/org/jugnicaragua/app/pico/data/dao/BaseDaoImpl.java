package org.jugnicaragua.app.pico.data.dao;

import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 * Clase base para la creación de clases dao, la clase define las operaciones DML básicas dentro de
 * una base de datos relacional como son SELECT, UPDATE, DELETE.
 *
 * @param <T>  Clase de entidad
 * @param <ID> Identificador de la entidad.
 * @author jselva
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, ID> implements BaseDao<T, ID>, Closeable {

  private final Session SESSION;
  private final String TABLE_NAME;

  public BaseDaoImpl(SessionFactory factory, Class<T> entityClass) {
    Annotation entityAnnotation = entityClass.getAnnotation(Entity.class);
    if (entityAnnotation == null) {
      throw new RuntimeException(
          "La clase " + entityClass.getName() + " debe estar anotada con @Entity");
    }

    Table tableAnnotation = entityClass.getAnnotation(Table.class);
    if (tableAnnotation != null) {
      this.TABLE_NAME = tableAnnotation.name();
    } else {
      this.TABLE_NAME = entityClass.getName();
    }

    this.SESSION = factory.openSession();
  }

  @Override
  public String getTableName() {
    return this.TABLE_NAME;
  }

  @Override
  public Session getSession() {
    return this.SESSION;
  }

  @Override
  public T get(ID id) {
    Query<T> query = this.SESSION.createQuery(" From " + this.TABLE_NAME + " where Id = :id");
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  @Override
  public List<T> getAll() {
    Query<T> query = this.SESSION.createQuery(" From " + this.TABLE_NAME);
    return query.getResultList();
  }

  @Override
  public void save(T entity) {
    Transaction transaction = this.SESSION.beginTransaction();
    try {
      this.SESSION.saveOrUpdate(entity);
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      throw e;
    }
  }

  @Override
  public void saveAll(List<T> entities) {
    Transaction transaction = this.SESSION.beginTransaction();
    try {
      for (var entity : entities) {
        this.SESSION.persist(entity);
      }
      this.SESSION.flush();
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      throw e;
    }
  }

  @Override
  public void remove(T entity) {
    Transaction transaction = this.SESSION.beginTransaction();
    try {
      this.SESSION.remove(entity);
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      throw e;
    }
  }

  @Override
  public void removeAll() {
    Transaction transaction = this.SESSION.beginTransaction();
    try {
      this.SESSION.createQuery("delete " + this.TABLE_NAME).executeUpdate();
      transaction.commit();
    } catch (Exception exception) {
      transaction.rollback();
      throw exception;
    }
  }

  @Override
  public void close() {
    this.SESSION.close();
  }

}
