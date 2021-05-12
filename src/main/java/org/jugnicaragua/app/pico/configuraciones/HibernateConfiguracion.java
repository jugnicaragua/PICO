package org.jugnicaragua.app.pico.configuraciones;

import java.io.Closeable;
import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jugnicaragua.app.pico.data.entidades.Menu;

public class HibernateConfiguracion implements Closeable {

  private final Configuration configuration;
  private SessionFactory sessionFactory;

  public HibernateConfiguracion() {
    configuration = new Configuration();
  }

  public void cargar() {
    this.configuration.addAnnotatedClass(Menu.class);
    this.configuration.configure("hibernate.cfg.xml");
    this.sessionFactory = this.configuration.buildSessionFactory();
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Override
  public void close() throws IOException {
    if (this.sessionFactory != null && this.sessionFactory.isOpen()) {
      this.sessionFactory.close();
    }
  }
}
