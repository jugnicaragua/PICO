package org.ni.jug.pico.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Clase que representa la entidad o tabla CuentaTipo.
 *
 * @author Gustavo Castro <gacsnic75@gmail.com>
 */
@Entity
@Table(name = "CuentaTipo",
        indexes = {@Index(name = "idx_cuenta_tipo", columnList = "Descripcion")})
@SequenceGenerator(name = "sequence")
public class CuentaTipo extends Identificador<Short> {

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    public CuentaTipo() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CuentaTipo{" +
                "descripcion='" + descripcion + '\'' +
                ", id=" + id +
                '}';
    }
}
