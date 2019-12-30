package org.jugni.apps.pico.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugni.apps.pico.modelos.MiEmpresa;

/**
 * Clase implementar persistencia de datos
  * 
 *  @author  :Gustavo Castro <gacsnic75@gmail.com>
 *  @version :1.0 
 */
public class EmpresaImpl implements ActualizarRegistroDao<MiEmpresa>,ObtenerRegistroDao<MiEmpresa>{

     private final Session session = HibernateUtil.getSessionFactory().openSession();

     /**
      * Agrega el regsitro de la empresa si no existe, si existe actauliza las modificaciones 
      * @param registro  
      */
     @Override
     public void actualizarRegistro(MiEmpresa registro) {
          Transaction transaction = session.beginTransaction();

          session.saveOrUpdate(registro);
          transaction.commit();
     }


     public void close() {
          session.close();
     }
     /**
      * Devuelve registro de la base de datos
      * @return 
      */
     @Override
     public MiEmpresa obtenerRegistro() {
          return (MiEmpresa) session.createQuery("From MiEmpresa").getSingleResult();
     }

}
