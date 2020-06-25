package org.jugni.apps.pico.security;

import java.util.ArrayList;
import java.util.List;
import org.jugni.apps.pico.security.exception.InvalidAccessException;
import org.jugni.apps.pico.security.model.UserInfo;
import org.jugni.apps.pico.ui.util.BaseInternalView;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.security.annotation.Access;

public final class UserSession {

  private final HibernateHelper helper;
  private final List<BaseInternalView> openViews;
  private UserInfo userInfo;

  public UserSession(HibernateHelper helper) {
    this.helper = helper;
    this.openViews = new ArrayList<>();
  }

  private void invalidateSession() {
    this.userInfo = null;
    for (var view : openViews) {
      ((AppSessionEvent) view).invalidate();
      unregisterAccess(view);
    }
  }

  public void login() throws InvalidAccessException {
    this.userInfo = new UserInfo();
  }

  public void logout() {
    this.userInfo = null;
    for (var view : openViews) {
      ((AppSessionEvent) view).logout();
      unregisterAccess(view);
    }
  }

  public void registerAccess(BaseInternalView view) {
    if (this.isLoggin()) {
      Access access = view.getClass().getAnnotation(Access.class);
      if (access != null) {
        if (userInfo.getRol().equals(access.rol())) {
          openViews.add(view);
        } else {
          ((AppSessionEvent) view).invalidRol();
        }
      } else {
        throw new RuntimeException("La vista interna debe de estar anotada con Access");
      }
    } else {
      this.invalidateSession();
    }
  }

  public void unregisterAccess(BaseInternalView view) {
    openViews.remove(view);
  }

  public boolean isLoggin() {
    return this.userInfo != null;
  }
}
