package org.jugni.apps.pico.DAO;

import java.util.List;

/**
 * Interface para obtener los registro de la base de datos 
 * @author gacs
 * @param <T>
 * @param <K>
 */
public interface ListarRegistrosDao <T>{
      List<T>  getRegistros();
      T getRegistroWhere(T registro);
      List<T>  getRegistrosWhere(T registro);
}
