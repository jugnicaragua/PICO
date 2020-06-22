package org.jugni.apps.pico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jugni.apps.pico.DAO.CuentaTipoDao;
import org.jugni.apps.pico.modelos.CuentaTipo;
import org.junit.Test;

public class TestCuentaTipoDao {

  @Test
  public void testCreateSession() {
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      assertNotNull("El factory debe ser no null", factory);
      Session session = factory.openSession();
      assertNotNull("La session no puede ser null", session);
      session.close();
    }
  }

  @Test
  public void testInsertData() {
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
    try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
      try (CuentaTipoDao cuentaTipoDao = new CuentaTipoDao(factory)) {

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
