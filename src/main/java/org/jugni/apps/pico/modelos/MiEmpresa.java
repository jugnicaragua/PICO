package org.jugni.apps.pico.modelos;

import java.util.Objects;
import javax.persistence.*;

/**
 *Representa la entidad Empresa
 * @author gacs
 */
@Entity
public class MiEmpresa {
     @Id
     @Column(name = "Ruc", length = 25)
     private String ruc;
     
     @Column(name = "RazonSocial", length = 35)
     private String razonSocial;

     @Column(name = "EmitirA", length = 35)
     private String emitirA;

     @Column(name = "Direccion", length = 250)
     private String direccion;

     @Column(name = "RepresentanteLegal", length = 35)
     private String representanteLegal;

     @Column(name = "Contacto", length = 35)
     private String contacto;

     @Column(name = "Web", length = 35)
     private String webSite;

     @Column(name = "Correo", length = 50)
     private String correo;

     @Column(name = "Telefono", length = 35)
     private String telefono;

     @Column(name = "ImgUbicacion", length = 250)
     private String imgUbicacion; //Almacena la direccion de la imagen de la Empresa
     
     public MiEmpresa() {
     }

     public MiEmpresa(String ruc, String razonSocial, String emitirA, String direccion, String representanteLegal, String contacto, String webSite, String correo, String telefono, String imgUbicacion) {
          this.ruc = ruc;
          this.razonSocial = razonSocial;
          this.emitirA = emitirA;
          this.direccion = direccion;
          this.representanteLegal = representanteLegal;
          this.contacto = contacto;
          this.webSite = webSite;
          this.correo = correo;
          this.telefono = telefono;
          this.imgUbicacion = imgUbicacion;
     }

     public String getRuc() {
          return ruc;
     }

     public void setRuc(String ruc) {
          this.ruc = ruc;
     }

     public String getRazonSocial() {
          return razonSocial;
     }

     public void setRazonSocial(String razonSocial) {
          this.razonSocial = razonSocial;
     }

     public String getEmitirA() {
          return emitirA;
     }

     public void setEmitirA(String emitirA) {
          this.emitirA = emitirA;
     }

     public String getdireccion() {
          return direccion;
     }

     public void setdireccion(String direccion) {
          this.direccion = direccion;
     }

     public String getRepresentanteLegal() {
          return representanteLegal;
     }

     public void setRepresentanteLegal(String representanteLegal) {
          this.representanteLegal = representanteLegal;
     }

     public String getContacto() {
          return contacto;
     }

     public void setContacto(String contacto) {
          this.contacto = contacto;
     }

     public String getWebSite() {
          return webSite;
     }

     public void setWebSite(String webSite) {
          this.webSite = webSite;
     }

     public String getCorreo() {
          return correo;
     }

     public void setCorreo(String correo) {
          this.correo = correo;
     }

     public String getTelefono() {
          return telefono;
     }

     public void setTelefono(String telefono) {
          this.telefono = telefono;
     }

     public String getImgUbicacion() {
          return imgUbicacion;
     }

     public void setImgUbicacion(String imgUbicacion) {
          this.imgUbicacion = imgUbicacion;
     }

     @Override
     public int hashCode() {
          int hash = 7;
          hash = 79 * hash + Objects.hashCode(this.ruc);
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
          final MiEmpresa other = (MiEmpresa) obj;
          return Objects.equals(this.ruc, other.ruc);
     }

     @Override
     public String toString() {
          return "Empresa{" + "ruc=" + ruc + ", razonSocial=" + razonSocial + ", EmitirA=" + emitirA + ", Direccion=" + direccion + ", representanteLegal=" + representanteLegal + ", contacto=" + contacto + ", webSite=" + webSite + ", correo=" + correo + ", telefono=" + telefono + ", imgUbicacion=" + imgUbicacion + '}';
     }
     
     
}
