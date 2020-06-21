package org.ni.jug.pico.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Gustavo Castro <gacsnic75@gmail.com>
 */
@Entity
@SequenceGenerator(name = "sequence")
public class ComprobanteCuenta extends Identificador<Long> {

    @Column(name = "Credito")
    private Float credito;

    @Column(name = "Debito")
    private Float debito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ComprobanteId")
    private Comprobante comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaId")
    private Cuenta cuenta;

    public ComprobanteCuenta() {
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
