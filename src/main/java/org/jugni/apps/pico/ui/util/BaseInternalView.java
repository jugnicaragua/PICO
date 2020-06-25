package org.jugni.apps.pico.ui.util;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.security.UserSession;
import org.jugni.apps.pico.security.exception.InvalidAccessException;
import org.jugni.apps.pico.security.SessionEvent;

public abstract class BaseInternalView extends JInternalFrame implements SessionEvent {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final PicoApplication application;
  private final HibernateHelper hibernateHelper;
  private final UserSession userSession;

  public BaseInternalView(PicoApplication application) throws InvalidAccessException {
    this.application = application;
    this.userSession = this.application.getRootView().getUserSession();
    this.hibernateHelper = this.application.getHibernateHelper();

    this.userSession.registerAccess(this);
    this.initView();
    application.getRootView().setCurrentStatus(getTitle());
  }

  protected final UserSession getAppSession() {
    return this.userSession;
  }

  protected final HibernateHelper getHibernateHelper() {
    return this.hibernateHelper;
  }

  protected final PicoApplication getApplication() {
    return this.application;
  }

  protected abstract void initView();

  protected abstract void close();

  @Override
  public void logout() {
    this.dispose();
    this.close();
  }

  @Override
  public void invalidSession() {
    var options = new Object[] {"Aceptar"};
    JOptionPane.showOptionDialog(null, "Sessión invalida", "Error", JOptionPane.DEFAULT_OPTION,
        JOptionPane.ERROR_MESSAGE, null, options, options[0]);

    application.getRootView().setCurrentStatus("Error: Sessión invalida");

    this.dispose();
    this.close();
  }

  @Override
  public void invalidRol() {
    var options = new Object[] {"Aceptar"};
    JOptionPane.showOptionDialog(null, "No tiene acceso a esta opción", "Error",
        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

    application.getRootView().setCurrentStatus("Error: No tiene acceso a esta opción");

    this.dispose();
    this.close();
  }
}
