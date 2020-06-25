package org.jugni.apps.pico.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.data.model.CuentaTipo;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCuentaTipoDao {

  private static Configuration configuration;

  @BeforeClass
  public static void setup() {
    Properties settings = new Properties();
    settings.put(Environment.DRIVER, "org.sqlite.JDBC");
    settings.put(Environment.URL, "jdbc:sqlite:pico.db");
    settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLiteDialect");
    settings.put(Environment.SHOW_SQL, "true");
    settings.put(Environment.HBM2DDL_AUTO, "update");

    configuration = new Configuration();
    configuration.setProperties(settings);
    configuration.addAnnotatedClass(CuentaTipo.class);
  }

  @Test
  public void testCreateSession() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      assertNotNull("El factory debe ser no null", sessionFactory);
      Session session = sessionFactory.openSession();
      assertNotNull("La session no puede ser null", session);
      session.close();
    }
  }

  @Test
  public void testInsertData() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        cuentaTipoDao.removeAll();

        CuentaTipo cuentaTipo = new CuentaTipo();
        cuentaTipo.setDescripcion("Prueba");
        cuentaTipoDao.save(cuentaTipo);

        List<CuentaTipo> allCuentaTipo = cuentaTipoDao.getAll();

        assertNotNull(allCuentaTipo);
        assertEquals(1, allCuentaTipo.size());

        for (CuentaTipo c : cuentaTipoDao.getAll()) {
          assertEquals("Prueba", c.getDescripcion());
        }
      }
    }
  }

  @Test
  public void testUpdateData() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        cuentaTipoDao.removeAll();

        CuentaTipo cuentaTipo = new CuentaTipo();
        cuentaTipo.setDescripcion("Prueba");
        cuentaTipoDao.save(cuentaTipo);

        List<CuentaTipo> allCuentaTipo = cuentaTipoDao.getAll();

        assertNotNull(allCuentaTipo);
        assertEquals(1, allCuentaTipo.size());

        CuentaTipo c = cuentaTipoDao.getAll().get(0);
        c.setDescripcion("Modificado");
        cuentaTipoDao.save(c);

        CuentaTipo cModificado = cuentaTipoDao.getAll().get(0);
        assertEquals("Modificado", cModificado.getDescripcion());
      }
    }
  }

  @Test
  public void testGetAllRows() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        cuentaTipoDao.removeAll();

        {
          CuentaTipo cuentaTipo = new CuentaTipo();
          cuentaTipo.setDescripcion("Prueba");
          CuentaTipo cuentaTipo2 = new CuentaTipo();
          cuentaTipo2.setDescripcion("Prueba2");

          cuentaTipoDao.saveAll(Arrays.asList(cuentaTipo, cuentaTipo2));
        }

        List<CuentaTipo> cuentaTipos = cuentaTipoDao.getAll();
        assertNotNull("La lista no puede ser null", cuentaTipos);
        assertEquals(2, cuentaTipos.size());
        System.out.println("Size is " + cuentaTipos.size());
        for (var cuentaTipo : cuentaTipos) {
          System.out.println(cuentaTipo);
        }
      }
    }
  }

  @Test
  public void testGetSingleRow() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        cuentaTipoDao.removeAll();

        CuentaTipo cuentaTipo = new CuentaTipo();
        cuentaTipo.setDescripcion("Prueba");
        cuentaTipoDao.save(cuentaTipo);

        List<CuentaTipo> cuentaTipos = cuentaTipoDao.getAll();
        Short id = cuentaTipos.get(0).getId();
        CuentaTipo result = cuentaTipoDao.get(id);
        assertNotNull(result);
      }
    }
  }

  @Test
  public void testClearAllData() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        CuentaTipo cuentaTipo = new CuentaTipo();
        cuentaTipo.setDescripcion("Prueba");
        cuentaTipoDao.save(cuentaTipo);

        cuentaTipoDao.removeAll();

        List<CuentaTipo> allCuentaTipo = cuentaTipoDao.getAll();
        assertEquals(0, allCuentaTipo.size());
      }
    }
  }

  @Test
  public void testRemoveSingleRow() {
    try (var hibernateHelper = new HibernateHelper(configuration)) {
      SessionFactory sessionFactory = hibernateHelper.getSessionFactory();
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(sessionFactory)) {

        cuentaTipoDao.removeAll();

        CuentaTipo cuentaTipo = new CuentaTipo();
        cuentaTipo.setDescripcion("Prueba");
        cuentaTipoDao.save(cuentaTipo);

        List<CuentaTipo> cuentaTipos = cuentaTipoDao.getAll();
        Short id = cuentaTipos.get(0).getId();
        CuentaTipo result = cuentaTipoDao.get(id);
        assertNotNull(result);

        cuentaTipoDao.remove(result);
        assertEquals(0, cuentaTipoDao.getAll().size());

      }
    }
  }

}
