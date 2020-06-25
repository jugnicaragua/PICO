package org.jugni.apps.pico.ui;

import javax.swing.*;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.security.UserSession;
import org.jugni.apps.pico.ui.menu.MainMenu;
import java.awt.*;

@SuppressWarnings("serial")
public final class MainView extends JFrame implements MainMenu.MenuAction {

  private PicoApplication application;
  private UserSession session;

  private JDesktopPane container;
  private JToolBar mState;
  private JLabel mCurrentStatus;

  private final MainMenu mainMenu;

  public MainView(PicoApplication application) {
    this.application = application;
    this.session = new UserSession(application.getHibernateHelper());
    this.mainMenu = new MainMenu(application, this);
    this.session.verifyAdmin();
    initView();
  }

  public final PicoApplication getApplication() {
    return this.application;
  }

  public final UserSession getUserSession() {
    return this.session;
  }

  public final MainMenu getMenu() {
    return this.mainMenu;
  }

  public void setCurrentStatus(String status) {
    this.mCurrentStatus.setText(status);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initView() {
    setTitle("PICO :: Sistema Contable");
    setName("vPrincipal");
    setBounds(100, 100, 626, 375);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(MAXIMIZED_BOTH);

    {// Toolbar State
      this.mState = new JToolBar();
      this.mState.setMargin(new Insets(8, 8, 8, 8));
      this.mState.setFloatable(false);
      this.mState.setLayout(new BorderLayout());

      getContentPane().add(mState, BorderLayout.SOUTH);

      this.mCurrentStatus = new JLabel("");
      this.mState.add(this.mCurrentStatus, BorderLayout.WEST);
    }

    container = new JDesktopPane();
    getContentPane().add(container, BorderLayout.CENTER);

    setJMenuBar(this.mainMenu);
  }

  /**
   * Agrega y centra la ventana hija al contenedor
   *
   * @param ventanaInterna
   */
  @Override
  public void addView(JInternalFrame view) {
    container.add(view);
    Dimension dskSize = container.getSize();
    Dimension frmSize = view.getSize();
    view.setLocation((dskSize.width - frmSize.width) / 2, (dskSize.height - frmSize.height) / 2);
    view.setVisible(true);
  }


  public void agregarAlEscritorio(JInternalFrame ventanaInterna) {
    container.add(ventanaInterna);
    Dimension dskSize = container.getSize();
    Dimension frmSize = ventanaInterna.getSize();
    ventanaInterna.setLocation((dskSize.width - frmSize.width) / 2,
        (dskSize.height - frmSize.height) / 2);
    ventanaInterna.setVisible(true);
  }
}
