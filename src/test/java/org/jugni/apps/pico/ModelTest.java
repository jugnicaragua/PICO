package org.jugni.apps.pico;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugni.apps.pico.modelos.Cuenta;
import org.jugni.apps.pico.modelos.TipoCuenta;
import org.jugni.apps.pico.vista.swing.HibernateUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModelTest {
    private static TipoCuenta modelo1;
    private static TipoCuenta modelo2;
    private List<TipoCuenta> lstDatos;
    final String SQLQUERY="FROM TipoCuenta";
    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		modelo1 = new TipoCuenta("Activo");
        modelo2 = new TipoCuenta("Circulante");
	}

	@Test
	public void test() {
	    Session session=HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction =session.beginTransaction() ;
        session.save(modelo1);
        session.save(modelo2);
        session.save(new Cuenta("01-00-00000","Activo",1,1,"Algo",modelo1));
        session.save(new Cuenta("01-01-00000","Circulante",1,1,"Algo",modelo2));
        transaction.commit();
        lstDatos= session.createQuery(SQLQUERY).getResultList();
        assertNotNull(lstDatos);
        assertEquals(2, lstDatos.size());
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		HibernateUtil.shutdown();
	}	
}
