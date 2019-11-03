package org.jugni.apps.pico.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
/**
 * Clase que representa la entidad o tabla TipoCuenta.
 *
 * @author gacs
 *
 */
@Entity
@Table(name="Comprobantes" ,
indexes = {@Index(name = "idx_tipocuenta", columnList="Descripcion")})
public class Comprobante {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "Numero", length = 8)
	private String numero; 
	@Column(name = "Concepto", length = 300)
	private String concepto;
	@Column(name = "Beneficiario", length = 10)
	private String beneficiario;
	@Column(name = "NumCheque", length = 10)
	private String numCheque;
	@Column(name = "Fecha")
	private Date fecha;
	@Column(name = "FechaComprobante")
	private Date fechaComprobante;
	@Column(name = "TipoComprobanteId")
	private int tipoComprobanteId;
	@Column(name = "estadoId")
	private int estadoId;
	
	
	public Comprobante() {
	}
	
	
	public Comprobante(Long id, String numero, String concepto, String beneficiario, String numCheque, Date fecha,
			Date fechaComprobante, int tipoComprobanteId, int estadoId) {
		this.id = id;
		this.numero = numero;
		this.concepto = concepto;
		this.beneficiario = beneficiario;
		this.numCheque = numCheque;
		this.fecha = fecha;
		this.fechaComprobante = fechaComprobante;
		this.tipoComprobanteId = tipoComprobanteId;
		this.estadoId = estadoId;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaComprobante() {
		return fechaComprobante;
	}
	public void setFechaComprobante(Date fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}
	public int getTipoComprobanteId() {
		return tipoComprobanteId;
	}
	public void setTipoComprobanteId(int tipoComprobanteId) {
		this.tipoComprobanteId = tipoComprobanteId;
	}
	public int getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
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
		Comprobantes other = (Comprobantes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comprobantes [id=" + id + ", numero=" + numero + ", concepto=" + concepto + ", beneficiario="
				+ beneficiario + ", numCheque=" + numCheque + ", fecha=" + fecha + ", fechaComprobante="
				+ fechaComprobante + ", tipoComprobanteId=" + tipoComprobanteId + ", estadoId=" + estadoId + "]";
	}
	
	
	
	
	
	
}
