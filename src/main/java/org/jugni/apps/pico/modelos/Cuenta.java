package org.jugni.apps.pico.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Clase que representa la entidad o tabla Cuentas.
 * 
 * @author Nadie.
 *
 */
@Entity(name = "Cuentas")
public class Cuenta {
  @Id
  @GeneratedValue
  Long id; // Usamos una Clase en lugar de una primitiva por mejor practica.
  private Long idCuenta;
  private Long IdTipoCuenta;
  private String codigo;
  private String descripcion;

  /**
   * Constructor Vacio requerido
   */
  public Cuenta() {
    
  }
  
  
}
