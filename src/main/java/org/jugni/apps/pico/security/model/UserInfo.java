package org.jugni.apps.pico.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.jugni.apps.pico.security.Rol;

@Entity
@Table(name = "user")
public class UserInfo {

  @Id
  private String username;

  private Rol rol;

  private Boolean active;

  public String getUsername() {
    return this.username;
  }

  public Rol getRol() {
    return this.rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getActive() {
    return this.active;
  }
}
