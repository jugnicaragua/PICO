package org.jugni.apps.pico.DAO;

import org.hibernate.SessionFactory;
import org.jugni.apps.pico.modelos.CuentaTipo;

public class CuentaTipoDao extends BaseDao<CuentaTipo, Short> {

  public CuentaTipoDao(SessionFactory factory) {
    super(factory, CuentaTipo.class);
  }
  
}
