package org.jugni.apps.pico.security;

public interface AppSessionEvent {
  public void logout();

  public void invalidate();
  
  public void invalidRol();
}
