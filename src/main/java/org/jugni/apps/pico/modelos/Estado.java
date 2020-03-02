/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugni.apps.pico.modelos;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Clase que representa la entidad o tabla Estado.
 * org.jugni.apps.pico.modelos.Estado
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @license : GPLv3
 */
@Entity
public class Estado {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    @OneToOne(mappedBy = "estado",
            cascade = CascadeType.ALL)
    private Cuenta cuenta;

    /**
     * Constructor Vacio requerido, para cuando no se tiene los valores al
     * instanciar la clase Estado
     */
    public Estado() {
    }

    public Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", descripcion=" + descripcion + ", cuenta=" + cuenta + '}';
    }

}
