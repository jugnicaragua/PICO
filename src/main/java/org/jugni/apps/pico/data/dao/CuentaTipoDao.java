package org.jugni.apps.pico.data.dao;

import org.hibernate.SessionFactory;
import org.jugni.apps.pico.data.model.CuentaTipo;

public class CuentaTipoDao extends BaseDao<CuentaTipo, Short> {

  public CuentaTipoDao(SessionFactory factory) {
    super(factory, CuentaTipo.class);
  }

}
