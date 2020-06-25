package org.jugni.apps.pico.data.dao;

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
 * class ClaseDao extends BaseDao<Clase, Integer> {
 * 
 * public ClaseDao(SessionFactory factory){ super(factory, Clase.class) }
 * 
 * }
 * 
 * try (var dao = new ClaseDao()) {
 * 
 * //operations dao.save(T entity); ...
 * 
 * }
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
  private final String HQL_TABLE_NAME;

  /**
   * Constructor por defecto
   * 
   * @param factory el session factory utilizado para la obtención de Session
   * @param entityClass Class del type generico, utilizado para obtener propiedades de la clase.
   */
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

    this.HQL_TABLE_NAME = entityClass.getName();
    this.SESSION = factory.openSession();
  }

  /**
   * Devuelve el nombre de la tabla de base de datos.
   * 
   * @return Table Name.
   */
  public String getTableName() {
    return this.TABLE_NAME;
  }

  /**
   * Devuelve el nombre de la tabla para consultas HQL.
   * 
   * @return Table Name.
   */
  public String getHqlTableName() {
    return this.HQL_TABLE_NAME;
  }

  /**
   * Obtiene la session para la creación de nuevos metodos en las clases hijas.
   * 
   * @return Session
   */
  protected Session getSession() {
    return this.SESSION;
  }

  /**
   * Devuelve un unico elemento de base de datos, utilizando el id de la tabla.
   * 
   * @param id
   * @return Una instancia de <T> o null.
   */
  public T get(ID id) {
    Query<T> query = this.SESSION.createQuery(" From " + this.HQL_TABLE_NAME + " where Id = :id");
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  /**
   * Obtiene todos los registros de una base de datos.
   * 
   * @return una lista de elementos <T> o una lista vacia.
   */
  public List<T> getAll() {
    Query<T> query = this.SESSION.createQuery(" From " + this.HQL_TABLE_NAME);
    return query.getResultList();
  }

  /**
   * Inserta o actualiza un registro en la base de datos.
   * 
   * @param entity La entidad a actualizar o insertar.
   */
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

  /**
   * Guarda una lista de elementos en la base de datos.
   * 
   * @param entities La lista de elementos a guardar.
   */
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

  /**
   * Elimina un registro de la base de datos.
   * 
   * @param entity Entidad a ser eliminada.
   */
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

  /**
   * Elimina todos los registros de la tabla en la base de datos.
   */
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
