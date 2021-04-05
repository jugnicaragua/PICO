package org.jugnicaragua.app.pico.dao;

import org.hibernate.SessionFactory;
import org.jugnicaragua.app.pico.modelo.CuentaTipo;

public class CuentaTipoDao extends BaseDao<CuentaTipo, Short> {

  public CuentaTipoDao(SessionFactory factory) {
    super(factory, CuentaTipo.class);
  }
  
}
