package org.jugni.apps.pico.security.model;

public class UserInfo {
  private String username;
  private Boolean active;
  private Rol rol;

  public UserInfo(String username, Boolean active, Rol rol) {
    this.username = username;
    this.active = active;
    this.rol = rol;
  }

  public String getUsername() {
    return username;
  }

  public Boolean getActive() {
    return active;
  }

  public Rol getRol() {
    return rol;
  }


}
