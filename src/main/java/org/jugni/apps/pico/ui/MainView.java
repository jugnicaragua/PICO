package org.jugni.apps.pico.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.security.UserSession;
import org.jugni.apps.pico.ui.menu.MainMenu;
import java.awt.*;

@SuppressWarnings("serial")
public final class MainView extends JFrame {

  private final PicoApplication application;
  private final UserSession session;
  private JDesktopPane desktopPane;

  public MainView(PicoApplication application) {
    this.application = application;
    this.session = new UserSession(application.getHibernateHelper());
    initView();
  }

  public final PicoApplication getApplication() {
    return this.application;
  }

  public final UserSession getUserSession() {
    return this.session;
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

    JToolBar toolBar_1 = new JToolBar();
    getContentPane().add(toolBar_1, BorderLayout.SOUTH);

    JLabel lblFecha = new JLabel("Fecha: ");
    lblFecha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    toolBar_1.add(lblFecha);

    JLabel lblPeriodoFiscal = new JLabel("Periodo Fiscal");
    toolBar_1.add(lblPeriodoFiscal);

    JLabel lblNewLabel = new JLabel("New label");
    toolBar_1.add(lblNewLabel);

    JProgressBar progressBar = new JProgressBar();
    toolBar_1.add(progressBar);

    JToolBar toolBar = new JToolBar();
    getContentPane().add(toolBar, BorderLayout.NORTH);

    JButton btnNewButton = new JButton("Nuevo CD");
    btnNewButton.setIcon(new ImageIcon(MainView.class
        .getResource("/org/tango-project/tango-icon-theme/16x16/actions/document-new.png")));
    toolBar.add(btnNewButton);

    desktopPane = new JDesktopPane();
    getContentPane().add(desktopPane, BorderLayout.CENTER);
    // Agregando el menu principal a la ventana
    setJMenuBar(new MainMenu());
  }

  /**
   * Metodo para mostrar, porque esta es una clase estandart, que no hereda ninguna otra clase,
   * ergo, debemos usar un Wrapper.
   */
  public void mostrar() {
    setVisible(true);
  }

  public void agregar(JInternalFrame ventanaInterna) {
    add(ventanaInterna);
  }

  /**
   * Agrega las ventanas JInternalFrame al escritorio(desktopPane) Centra la ventada en el
   * desktopPane
   *
   * @param ventanaInterna
   */
  public void agregarAlEscritorio(JInternalFrame ventanaInterna) {
    desktopPane.add(ventanaInterna);
    Dimension dskSize = desktopPane.getSize();
    Dimension frmSize = ventanaInterna.getSize();
    ventanaInterna.setLocation((dskSize.width - frmSize.width) / 2,
        (dskSize.height - frmSize.height) / 2);
    ventanaInterna.setVisible(true);
  }
}
