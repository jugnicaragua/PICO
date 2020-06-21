package org.ni.jug.pico.core.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Clase que representa la entidad o tabla Cierre.
 * org.jugni.apps.pico.modelos.Cierre
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @license : GPLv3
 */
@Entity
public class Cierre {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Anio")
    private Short anio;

    @Column(name = "Mes")
    private Byte mes;

    @Column(name = "Fecha")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CierreTipoId")
    private CierreTipo cierreTipo;

    @OneToMany(mappedBy = "cierre",
            cascade = CascadeType.ALL)
    private List<CuentaCierre> cuentaCierre;

    /**
     * Constructor Vacio requerido, para cuando no se tiene los valores al
     * instanciar la clase Cierre
     */
    public Cierre() {
    }

    public Cierre(Long id, Short anio, Byte mes, Date fecha, CierreTipo cierreTipo, List<CuentaCierre> cuentaCierre) {
        this.id = id;
        this.anio = anio;
        this.mes = mes;
        this.fecha = fecha;
        this.cierreTipo = cierreTipo;
        this.cuentaCierre = cuentaCierre;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Byte getMes() {
        return mes;
    }

    public void setMes(Byte mes) {
        this.mes = mes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CierreTipo getCierreTipo() {
        return cierreTipo;
    }

    public void setCierreTipo(CierreTipo cierreTipo) {
        this.cierreTipo = cierreTipo;
    }

    public List<CuentaCierre> getCuentaCierre() {
        return cuentaCierre;
    }

    public void setCuentaCierre(List<CuentaCierre> cuentaCierre) {
        this.cuentaCierre = cuentaCierre;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Cierre other = (Cierre) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cierre{" + "id=" + id + ", anio=" + anio + ", mes=" + mes + ", fecha=" + fecha + ", cierreTipo=" + cierreTipo + ", cuentaCierre=" + cuentaCierre + '}';
    }



}
