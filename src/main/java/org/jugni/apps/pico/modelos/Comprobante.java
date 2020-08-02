package org.jugni.apps.pico.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Clase que representa la entidad o tabla Comprobante.
* org.jugni.apps.pico.modelos.Comprobante
 * 
 *  @author   :Gustavo Castro <gacsnic75@gmail.com>
 *  @license  : GPLv3
 */

@Entity
@Table(name="Comprobantes" ,
indexes = {@Index(name = "idx_tipocuenta", columnList="Descripcion")})
public class Comprobante extends Identificador<Long> {

    @Column(name = "Numero",length = 8)
    private String numero; 
    @Column(name = "Concepto",length = 300)
    private String concepto;
    @Column(name = "Beneficiario",length = 10)
    private String beneficiario;
    @Column(name = "NumCheque",length = 10)
    private String numCheque;
    @Column(name = "Fecha")
    private LocalDate fecha;
//	@Column(name = "FechaComprobante")
//	private Date fechaComprobante;
    @Column(name = "estadoId")
    private int estadoId;
    @OneToMany(mappedBy = "comprobante",
    cascade = CascadeType.ALL)
    private List<ComprobanteCuenta> comprobantesCuenta=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ComprobanteTipoId")
    private ComprobanteTipo comprobanteTipo;

  /**
   * Constructor Vacio requerido, para cuando no se tiene los valores 
   * al instanciar la clase comprobante
   */
    public Comprobante() {
    }

    public Comprobante(String numero, String concepto, String beneficiario, String numCheque, LocalDate fecha, int estadoId) {
        this.numero = numero;
        this.concepto = concepto;
        this.beneficiario = beneficiario;
        this.numCheque = numCheque;
        this.fecha = fecha;
        this.estadoId = estadoId;
    }

    
    public ComprobanteTipo getComprobanteTipo() {
        return comprobanteTipo;
    }

    public void setComprobanteTipo(ComprobanteTipo comprobanteTipo) {
        this.comprobanteTipo = comprobanteTipo;
    }
	
	
    public List<ComprobanteCuenta> getComprobantesCuenta() {
        return comprobantesCuenta;
    }

    public void setComprobantesCuenta(List<ComprobanteCuenta> comprobantesCuenta) {
        this.comprobantesCuenta = comprobantesCuenta;
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

    public LocalDate getFecha() {
	return fecha;
    }

    public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
    }

    public int getEstadoId() {
	return estadoId;
    }

    public void setEstadoId(int estadoId) {
	this.estadoId = estadoId;
    }

    @Override
    public String toString() {
        return "Comprobante{" + "id=" + id + ", numero=" + numero + ", concepto=" + concepto + ", beneficiario=" + beneficiario + ", numCheque=" + numCheque + ", fecha=" + fecha + ", estadoId=" + estadoId + ", comprobantesCuenta=" + comprobantesCuenta + ", comprobanteTipo=" + comprobanteTipo + '}';
    }


	
}
