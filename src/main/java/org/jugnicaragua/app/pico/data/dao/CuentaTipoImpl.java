/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugnicaragua.app.pico.data.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jugnicaragua.app.pico.AplicacionPICO;
import org.jugnicaragua.app.pico.data.entidades.CuentaTipo;

/**
 * <strong> org.jugni.apps.pico.DAO </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 * Clase CuentaTipoImpl : implementar persistencia de datos de tipo de cuenta
 */
public class CuentaTipoImpl implements ObtenerRegistrosDao<CuentaTipo>, ActualizarRegistroDao<CuentaTipo> {

     private final Session session = AplicacionPICO.getINSTANCE().getDatabaseSession().openSession();

     @Override
     public List<CuentaTipo> obtenerRegistros() {
          List<CuentaTipo> CuentaTipoS = session.createQuery(" From CuentaTipo ").getResultList();
          session.close();
          return CuentaTipoS;
     }

     @Override
     public void actualizarRegistro(CuentaTipo registro) {
          //Intacia la transaccion
          Transaction transaction = session.beginTransaction();
          //Guarda la informacion en la base de datos
          session.saveOrUpdate(registro);
          //hace permanente los cambios
          transaction.commit();
          session.close();
     }

}
