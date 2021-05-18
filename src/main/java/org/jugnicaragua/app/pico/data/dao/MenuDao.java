package org.jugnicaragua.app.pico.data.dao;

import org.hibernate.SessionFactory;
import org.jugnicaragua.app.pico.data.entidades.Menu;

public class MenuDao extends BaseDaoImpl<Menu, Long> {

  public MenuDao(SessionFactory factory) {
    super(factory, Menu.class);
  }
}
