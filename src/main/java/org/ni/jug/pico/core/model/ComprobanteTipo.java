package org.ni.jug.pico.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gustavo Castro <gacsnic75@gmail.com>
 */
@Entity
@Table(name = "ComprobanteTipo",
        indexes = {@Index(name = "idx_comprobante_tipo", columnList = "Descripcion")})
public class ComprobanteTipo {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    @Column(name = "Nomenclatura", length = 3)
    private String nomenclatura;

    @OneToMany(mappedBy = "comprobanteTipo",
            cascade = CascadeType.ALL)
    private List<Comprobante> comprobantes = new ArrayList<>();

    public ComprobanteTipo() {
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

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public List<Comprobante> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }

}
