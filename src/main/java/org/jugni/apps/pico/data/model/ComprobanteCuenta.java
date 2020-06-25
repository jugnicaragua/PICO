
package org.jugni.apps.pico.data.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que representa la entidad o tabla ComprobanteCuenta.
* org.jugni.apps.pico.modelos.ComprobanteCuenta
 * 
 *  @author   :Gustavo Castro <gacsnic75@gmail.com>
 *  @license  : GPLv3
 */
@Entity
public class ComprobanteCuenta extends Identificador<Long>{

    /*    @Column(name="Fecha")
    private LocalDate fecha; */
    @Column(name="Credito")
    private Float credito;
    @Column(name="Debito")
    private Float debito;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ComprobanteId")
    private Comprobante comprobante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CuentaId")
    private Cuenta cuenta;
    
  /**
   * Constructor Vacio requerido, para cuando no se tiene los valores 
   * al instanciar la clase comprobantecuenta
   */
    public ComprobanteCuenta() {
    }

    public ComprobanteCuenta( Float credito, Float debito) {
        this.credito = credito;
        this.debito = debito;
    }

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    public Float getDebito() {
        return debito;
    }

    public void setDebito(Float debito) {
        this.debito = debito;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

}
