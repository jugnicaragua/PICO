package org.jugnicaragua.app.pico.data.dao;

import org.hibernate.SessionFactory;
import org.jugnicaragua.app.pico.data.entidades.CuentaTipo;

public class CuentaTipoDaoImpl extends BaseDaoImpl<CuentaTipo, Short> {

  public CuentaTipoDaoImpl(SessionFactory factory) {
    super(factory, CuentaTipo.class);
  }
  
}
