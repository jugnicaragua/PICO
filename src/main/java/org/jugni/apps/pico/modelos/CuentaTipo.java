package org.jugni.apps.pico.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Clase que representa la entidad o tabla CuentaTipo.
 *
 * @author gacs
 *
 */
@Entity
@Table(name="CuentaTipo" ,
indexes = {@Index(name = "idx_tipocuenta", columnList="Descripcion")})
public class CuentaTipo {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
    @Column(name = "Descripcion", length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "cuentaTipo",
        cascade = CascadeType.ALL)
    private List<Cuenta> cuentas= new ArrayList<>() ;
    
    public CuentaTipo(String descripcion){
	this.id = null;
	this.descripcion = descripcion;
    }
    /**
    * Constructor Vacio requerido, para cuando no se tiene los valores 
    * al instanciar la clase TipoCuenta
   */
    public CuentaTipo() {
    }

    /**
     * Devuelve el valor del Id
     * @return Long 
     */
    public Long getId() {
	return id;
    }
    /**
     * Establce el valor del id
     * @param id Long, puede recibir balor null
     */
    public void setId(Long id) {
    	this.id = id;
    }
    /**
     * Devuelve el nombre del tipo de cuenta
     * @return String
     */
    public String getDescripcion() {
	return descripcion;
    }
    /**
     * Establece la descripcion del tipo de cuenta
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
	
    /**
     * Nos devuelve las cuentas que se relacionan con el tipo de cuenta
     * @return List<Cuenta>
     */
    public List<Cuenta> getCuentas() {
	return cuentas;
    }
	
    /**
     * Establece las cuentas que le corresponde a tipo de cuenta
     * @param cuentas
     */
    public void setCuentas(List<Cuenta> cuentas) {
    	this.cuentas = cuentas;
    }
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
            return true;
	if (obj == null)
            return false;
	if (getClass() != obj.getClass())
            return false;
	CuentaTipo other = (CuentaTipo) obj;
	if (id == null) {
            if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
            return false;
		return true;
	}

    @Override
    public String toString() {
        return "CuentaTipo{" + "id=" + id + ", descripcion=" + descripcion + ", cuentas=" + cuentas + '}';
    }

}
