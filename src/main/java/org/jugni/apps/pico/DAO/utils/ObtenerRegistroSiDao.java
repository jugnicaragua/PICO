package org.jugni.apps.pico.DAO.utils;

/**
  * 
 *  @author  :Gustavo Castro <gacsnic75@gmail.com>
 *  @version :1.0 
 * @param <T> En la clase que lo implemente se sustituye por la clase entidad
 *
 *   Interface ObtenerUnRegistroDao : Interfas para obtener un registro de la base de datos condicionado por la clase registro
 */
public interface ObtenerRegistroSiDao <T> {
      T obtenerRegistroSi(T registro);
}
