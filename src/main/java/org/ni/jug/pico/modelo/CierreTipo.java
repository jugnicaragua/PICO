package org.ni.jug.pico.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase que representa la entidad o tabla Cierre.
 * org.jugni.apps.pico.modelos.CierreTipo
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @license : GPLv3
 */
@Entity
public class CierreTipo {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    @OneToMany(mappedBy = "cierreTipo",
            cascade = CascadeType.ALL)
    private List<Cierre> cierres = new ArrayList<>();
    /**
     * Constructor Vacio requerido, para cuando no se tiene los valores al
     * instanciar la clase CierreTipo
     */
    public CierreTipo() {
    }

    public CierreTipo(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cierre> getCierres() {
        return cierres;
    }

    public void setCierres(List<Cierre> cierres) {
        this.cierres = cierres;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final CierreTipo other = (CierreTipo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CierreTipo{" + "id=" + id + ", descripcion=" + descripcion + ", cierres=" + cierres + '}';
    }
    
    
}
