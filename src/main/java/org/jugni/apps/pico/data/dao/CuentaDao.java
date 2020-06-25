package org.jugni.apps.pico.data.dao;

import org.hibernate.SessionFactory;
import org.jugni.apps.pico.data.model.Cuenta;

public class CuentaDao extends BaseDao<Cuenta, Integer> {

  public CuentaDao(SessionFactory factory) {
    super(factory, Cuenta.class);
  }

}
