package org.jugnicaragua.app.pico.data.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugnicaragua.app.pico.AplicacionPICO;
import org.jugnicaragua.app.pico.data.entidades.MiEmpresa;

/**
 * Clase implementar persistencia de datos
  * 
 *  @author  :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 * 
 */
public class EmpresaImpl implements ActualizarRegistroDao<MiEmpresa>,ObtenerRegistroDao<MiEmpresa>{

     private final Session session =  AplicacionPICO.getINSTANCE().getDatabaseSession().openSession();

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
          Object obj;
          //Se captura execion para cuando la tabla esta sin registro.
          try{
               obj=session.createQuery(" From MiEmpresa ").getSingleResult();
          }catch (Exception e){
               obj = new MiEmpresa();
          }
          return (MiEmpresa) obj;
     }

}
