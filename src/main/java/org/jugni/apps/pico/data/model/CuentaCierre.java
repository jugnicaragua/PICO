package org.jugni.apps.pico.data.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que representa la entidad o tabla CuentaCierre.
 * org.jugni.apps.pico.modelos.CuentaCierre
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @license : GPLv3
 */
@Entity
public class CuentaCierre {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "SaldoInicialCredito")
    private Float saldoInicialCredito;

    @Column(name = "SaldoInicialDebito")
    private Float saldoInicialDebito;

    @Column(name = "SaldoFinalCredito")
    private Float saldoFinalCredito;

    @Column(name = "SaldoFinalDebito")
    private Float saldoFinalDebito;

    @Column(name = "Debito")
    private Float debito;

    @Column(name = "Credito")
    private Float credito;

    @Column(name = "Fecha")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CierreId")
    private Cierre cierre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaId")
    private Cuenta cuenta;
    /**
     * Constructor Vacio requerido, para cuando no se tiene los valores al
     * instanciar la clase CuentaCierre
     */

    public CuentaCierre() {
    }

    public CuentaCierre(Long id, Float saldoInicialCredito, Float saldoInicialDebito, Float saldoFinalCredito, Float saldoFinalDebito, Float debito, Float credito, Date fecha, Cierre cierre, Cuenta cuenta) {
        this.id = id;
        this.saldoInicialCredito = saldoInicialCredito;
        this.saldoInicialDebito = saldoInicialDebito;
        this.saldoFinalCredito = saldoFinalCredito;
        this.saldoFinalDebito = saldoFinalDebito;
        this.debito = debito;
        this.credito = credito;
        this.fecha = fecha;
        this.cierre = cierre;
        this.cuenta = cuenta;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSaldoInicialCredito() {
        return saldoInicialCredito;
    }

    public void setSaldoInicialCredito(Float saldoInicialCredito) {
        this.saldoInicialCredito = saldoInicialCredito;
    }

    public Float getSaldoInicialDebito() {
        return saldoInicialDebito;
    }

    public void setSaldoInicialDebito(Float saldoInicialDebito) {
        this.saldoInicialDebito = saldoInicialDebito;
    }

    public Float getSaldoFinalCredito() {
        return saldoFinalCredito;
    }

    public void setSaldoFinalCredito(Float saldoFinalCredito) {
        this.saldoFinalCredito = saldoFinalCredito;
    }

    public Float getSaldoFinalDebito() {
        return saldoFinalDebito;
    }

    public void setSaldoFinalDebito(Float saldoFinalDebito) {
        this.saldoFinalDebito = saldoFinalDebito;
    }

    public Float getDebito() {
        return debito;
    }

    public void setDebito(Float debito) {
        this.debito = debito;
    }

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cierre getCierre() {
        return cierre;
    }

    public void setCierre(Cierre cierre) {
        this.cierre = cierre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CuentaCierre other = (CuentaCierre) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CuentaCierre{" + "id=" + id + ", saldoInicialCredito=" + saldoInicialCredito + ", saldoInicialDebito=" + saldoInicialDebito + ", saldoFinalCredito=" + saldoFinalCredito + ", saldoFinalDebito=" + saldoFinalDebito + ", debito=" + debito + ", credito=" + credito + ", fecha=" + fecha + ", cierre=" + cierre + ", cuenta=" + cuenta + '}';
    }
    
    

}
