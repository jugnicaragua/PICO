package org.jugni.apps.pico.ui.util;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.security.UserSession;
import org.jugni.apps.pico.security.AppSessionEvent;

public abstract class BaseInternalView extends JInternalFrame implements AppSessionEvent {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final PicoApplication application;
  private final HibernateHelper hibernateHelper;
  private final UserSession userSession;

  public BaseInternalView(PicoApplication application) {
    this.application = application;
    this.userSession = this.application.getRootView().getUserSession();
    this.hibernateHelper = this.application.getHibernateHelper();
    this.userSession.registerAccess(this);
    this.initView();
  }

  protected UserSession getAppSession() {
    return this.userSession;
  }

  protected HibernateHelper getHibernateHelper() {
    return this.hibernateHelper;
  }


  protected abstract void initView();

  protected void close() {
    this.userSession.unregisterAccess(this);
  }

  @Override
  public void logout() {
    this.close();
    this.dispose();
  }

  @Override
  public void invalidate() {
    var options = new Object[] {"Aceptar"};
    JOptionPane.showOptionDialog(null, "Sessión invalida", "Error", JOptionPane.DEFAULT_OPTION,
        JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    this.close();
    this.dispose();
  }

  @Override
  public void invalidRol() {
    var options = new Object[] {"Aceptar"};
    JOptionPane.showOptionDialog(null, "No tiene acceso a esta opción", "Error",
        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    this.close();
    this.dispose();
  }
}
