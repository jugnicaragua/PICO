package org.jugni.apps.pico.data;

import java.io.Closeable;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jugni.apps.pico.data.model.*;
import org.jugni.apps.pico.security.model.User;

/**
 * Asistente para el manejo de la configuración y la obtención de session factory.
 *
 * @author gacs
 *
 */
public final class HibernateHelper implements Closeable {

  private final SessionFactory sessionFactory;

  public HibernateHelper() {
    this(null);
  }

  public HibernateHelper(Configuration configuration) {

    if (configuration == null) {
      Configuration config = new Configuration();

      config.addAnnotatedClass(Empresa.class);
      config.addAnnotatedClass(CuentaTipo.class);
      config.addAnnotatedClass(User.class);
      
      /*
       * config.addAnnotatedClass(Cierre.class); config.addAnnotatedClass(CierreTipo.class);
       * config.addAnnotatedClass(Comprobante.class);
       * config.addAnnotatedClass(ComprobanteCuenta.class);
       * config.addAnnotatedClass(ComprobanteTipo.class); config.addAnnotatedClass(Cuenta.class);
       * config.addAnnotatedClass(CuentaCierre.class);
       * 
       */

      this.sessionFactory = config.configure().buildSessionFactory();
    } else {
      ServiceRegistry serviceRegistry =
          new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

      this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


  }

  public SessionFactory getSessionFactory() {
    return this.sessionFactory;
  }

  @Override
  public void close() {
    if (this.sessionFactory != null) {
      this.sessionFactory.close();
    }
  }
}
