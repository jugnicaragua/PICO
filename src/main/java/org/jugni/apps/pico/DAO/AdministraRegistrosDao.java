package org.jugni.apps.pico.DAO;

import java.util.List;

/**
 * Interface para Insertar, borrar y actualizar  los registro de la base de datos 
 *
 * @author gacs
 * @param <T>
 */
public interface AdministraRegistrosDao<T> {
     void insertarRegistro(T registro);
     void insertarRegistros(List<T> lista);
     void actualizarRegistro(T registro);
     void borrarRegistros(T registro);
}
