package org.jugnicaragua.app.pico.dao;

import java.util.List;

/**
 * 
 * @author  :Gustavo Castro <gacsnic75@gmail.com>
 * @version :1.0 
 * @param <T> En la clase que lo implemente se sustituye por la clase entidad
 */
public interface CrearRegistroDao<T> {
     void insertarRegistro(T registro);
}
