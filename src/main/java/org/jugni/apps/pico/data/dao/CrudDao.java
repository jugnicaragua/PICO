package org.jugni.apps.pico.data.dao;


/**
 * 
 * @author  :Gustavo Castro <gacsnic75@gmail.com>
 * @version :1.0 
 * @param <T> En la clase que lo implemente se sustituye por la clase entidad
 */
public interface CrudDao<T> extends CrearRegistrosDao<T>,ActualizarRegistroDao<T>,
        ObtenerRegistroDao<T>,ObtenerRegistroSiDao<T>,ObtenerRegistrosDao<T>,BorrarRegistroDao<T>{
 
}
