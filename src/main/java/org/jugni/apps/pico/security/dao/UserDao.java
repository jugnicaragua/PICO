package org.jugni.apps.pico.security.dao;

import javax.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jugni.apps.pico.data.dao.BaseDao;
import org.jugni.apps.pico.security.model.Rol;
import org.jugni.apps.pico.security.model.User;

public final class UserDao extends BaseDao<User, String> {

  public UserDao(SessionFactory factory) {
    super(factory, User.class);
  }

  @SuppressWarnings("unchecked")
  public boolean existAdminUser() {
    try {
      Query<User> query =
          super.getSession().createQuery("FROM " + super.getHqlTableName() + " WHERE rol = :rol");
      query.setParameter("rol", Rol.ADMIN);
      return query.getSingleResult() != null;
    } catch (NoResultException e) {
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  public User getByUsername(String username) {
    Query<User> query = super.getSession()
        .createQuery("FROM " + super.getHqlTableName() + " WHERE Username = :username");
    query.setParameter("username", username);
    return query.getSingleResult();
  }
}
