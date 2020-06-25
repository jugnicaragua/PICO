package org.jugni.apps.pico.data.dao;

import org.hibernate.SessionFactory;
import org.jugni.apps.pico.data.model.Empresa;
import com.sun.istack.NotNull;

public class EmpresaDao extends BaseDao<Empresa, Short> {

  public EmpresaDao(@NotNull SessionFactory sessionFactory) {
    super(sessionFactory, Empresa.class);
  }
}
