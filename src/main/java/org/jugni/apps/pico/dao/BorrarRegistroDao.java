package org.jugni.apps.pico.dao;


/**
 * 
 * @author  :Gustavo Castro <gacsnic75@gmail.com>
 * @version :1.0 
 * @param <T> En la clase que lo implemente se sustituye por la clase entidad
 */
public interface BorrarRegistroDao<T> {
     void borrarRegistros(T registro);
}
