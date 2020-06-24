package org.jugni.apps.pico.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase util para crear SessionFactory
 *
 * @author gacs
 *
 */
public class HibernateUtil {

     private static SessionFactory sessionFactory;

     public static SessionFactory getSessionFactory() {

          if (sessionFactory == null) {

               try {
                    sessionFactory = new Configuration().configure().buildSessionFactory();

               } catch (HibernateException e) {

                    e.printStackTrace();

                    if (sessionFactory != null) {
                         sessionFactory.close();

                    }
               }
          }
          return sessionFactory;
     }

     public static void shutdown() {

          if (sessionFactory != null) {
               sessionFactory.close();
          }

     }
}
