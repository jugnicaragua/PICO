/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jugni.apps.pico.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugni.apps.pico.modelos.CuentaTipo;

/**
 *<strong> org.jugni.apps.pico.DAO </strong>
 *  @author  :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 *   Clase CuentaTipoImpl : implementar persistencia de datos de tipo de cuenta
 */
public class CuentaTipoImpl implements CrearRegistroDao<CuentaTipo>{
     private final Session session = HibernateUtil.getSessionFactory().openSession();
     @Override
     public void insertarRegistro(CuentaTipo registro) {
          //Intacia la transaccion
          Transaction transaction = session.beginTransaction();
          //Guarda la informacion en la base de datos
          session.save(registro);
          //hace permanente los cambios
          transaction.commit();
     }
     
    public void close() {
          session.close();
     }
}
