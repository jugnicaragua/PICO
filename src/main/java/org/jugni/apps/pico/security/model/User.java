package org.jugni.apps.pico.security.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  @Id
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private Rol rol;

  private Boolean active;

  public User() {

  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public Rol getRol() {
    return this.rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getActive() {
    return this.active;
  }

  public String getPassword() {
    return password;
  }
}
