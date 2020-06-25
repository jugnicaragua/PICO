package org.jugni.apps.pico.security;

public interface SessionEvent {
  public void logout();

  public void invalidSession();
  
  public void invalidRol();
}
