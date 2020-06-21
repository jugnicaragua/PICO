package org.ni.jug.pico.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * @author gacs
 */
@Entity
@Table(name = "Cuentas",
        indexes = {
                @Index(name = "idx_cuenta", columnList = "Descripcion,Naturaleza")})
@SequenceGenerator(name = "sequence")
public class Cuenta extends Identificador<Integer> {

    @Column(name = "cuenta", length = 25)
    private String cuenta;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    @Column(name = "Nivel")
    private int nivel;

    @Column(name = "Padre")
    private int padre;

    @Column(name = "Naturaleza", length = 50)
    private String naturaleza;

    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTipoId")
    private CuentaTipo cuentaTipo;

    @OneToMany(mappedBy = "cuenta",
            cascade = CascadeType.ALL)
    private List<ComprobanteCuenta> comprobantesCuenta;

    @OneToMany(mappedBy = "cuenta",
            cascade = CascadeType.ALL)
    private List<CuentaCierre> cuentaCierre;


    /**
     * Constructor Vacio requerido, para cuando no se tiene los valores al
     * instanciar la clase cuenta
     */
    public Cuenta() {

    }

    public Cuenta(String cuenta, String descripcion, int nivel, int padre, String naturaleza, CuentaTipo cuentaTipo) {
        this.cuenta = cuenta;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.padre = padre;
        this.naturaleza = naturaleza;
        this.cuentaTipo = cuentaTipo;
    }


    /**
     * Devuelve el valor del id de cuenta
     *
     * @return String
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Establece el valor de id de cuenta
     *
     * @param cuenta
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Devuelve el nombre de la cuenta
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el nombre de la cuenta
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el tipo de cuenta relacionado a la cuenta
     *
     * @return
     */
    public CuentaTipo getTipoCuenta() {
        return cuentaTipo;
    }

    /**
     * Establece el tipo de cuenta relacionado a la cuenta
     *
     * @param tipoCuenta
     */
    public void setTipoCuenta(CuentaTipo tipoCuenta) {
        this.cuentaTipo = tipoCuenta;
    }

    /**
     * Devuelve el nivel de la cuenta
     *
     * @return
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel de la cuenta
     *
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el valor de la cuenta padre
     *
     * @return int
     */
    public int getPadre() {
        return padre;
    }

    /**
     * Establece el valor de la cuenta padre
     *
     * @param padre
     */
    public void setPadre(int padre) {
        this.padre = padre;
    }

    /**
     * devuelve Nataruleza de la cuenta
     *
     * @return
     */
    public String getNaturaleza() {
        return naturaleza;
    }

    /**
     * Establece la nu
     *
     * @param naturaleza
     */
    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public CuentaTipo getCuentaTipo() {
        return cuentaTipo;
    }

    public void setCuentaTipo(CuentaTipo cuentaTipo) {
        this.cuentaTipo = cuentaTipo;
    }

    public List<ComprobanteCuenta> getComprobantesCuenta() {
        return comprobantesCuenta;
    }

    public void setComprobantesCuenta(List<ComprobanteCuenta> comprobantesCuenta) {
        this.comprobantesCuenta = comprobantesCuenta;
    }

    public List<CuentaCierre> getCuentaCierre() {
        return cuentaCierre;
    }

    public void setCuentaCierre(List<CuentaCierre> cuentaCierre) {
        this.cuentaCierre = cuentaCierre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + " ,cuenta " + cuenta + ", descripcion=" + descripcion + ", nivel=" + nivel + ", padre=" + padre + ", naturaleza=" + naturaleza + ", cuentaTipo=" + cuentaTipo + ", comprobantesCuenta=" + comprobantesCuenta + ", cuentaCierre=" + cuentaCierre + ", estado=" + estado + '}';
    }

}
