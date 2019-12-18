package org.jugni.apps.pico.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugni.apps.pico.modelos.MiEmpresa;

/**
 * Clase implementar persistencia de datos
 *
 * @author gacs
 */
public class EmpresaImpl implements AdministraRegistrosDao<MiEmpresa>, ListarRegistrosDao<MiEmpresa> {

     private Session session = HibernateUtil.getSessionFactory().openSession();

     @Override
     public void insertarRegistro(MiEmpresa registro) {
          Transaction transaction = session.beginTransaction();
          session.persist(registro);
          transaction.commit();
     }

     @Override
     public void insertarRegistros(List<MiEmpresa> lista) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public void actualizarRegistro(MiEmpresa registro) {
          System.out.println(registro);
          List<MiEmpresa> empresas = getRegistros();
          if (null == empresas || empresas.size() < 1) {
               insertarRegistro(registro);
               return ;
          }
          Transaction transaction = session.beginTransaction();

          session.update(registro);
          transaction.commit();
     }

     @Override
     public void borrarRegistros(MiEmpresa registro) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public List<MiEmpresa> getRegistros() {

          return session.createQuery("FROM MiEmpresa ").getResultList();
     }

     @Override
     public MiEmpresa getRegistroWhere(MiEmpresa registro) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public List<MiEmpresa> getRegistrosWhere(MiEmpresa registro) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     public void close() {
          session.close();
     }

}
