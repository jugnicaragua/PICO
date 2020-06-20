package org.jugni.apps.pico.DAO;

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
 * @author jselva
 *
 * @param <T> Clase de entidad
 * @param <ID> Identificador de la entidad.
 */
@SuppressWarnings("unchecked")
public class BaseDao<T, ID extends Object> implements Closeable {

  private final Session SESSION;
  private final String TABLE_NAME;

  public BaseDao(SessionFactory factory, Class<T> entityClass) {
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

  public String getTableName() {
    return this.TABLE_NAME;
  }

  protected Session getSession() {
    return this.SESSION;
  }

  public T get(ID id) {
    Query<T> query = this.SESSION.createQuery(" From " + this.TABLE_NAME + " where Id = id");
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  public List<T> getAll() {
    Query<T> query = this.SESSION.createQuery(" From " + this.TABLE_NAME + " q ");
    return query.getResultList();
  }

  public void save(T entity) {
    this.SESSION.saveOrUpdate(entity);
  }

  public void remove(T entity) {
    this.SESSION.remove(entity);
  }

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
