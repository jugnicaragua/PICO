package org.jugnicaragua.app.pico.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Closeable;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import org.jugnicaragua.app.pico.AplicacionPICO;
import org.jugnicaragua.app.pico.vista.menu.MenuPrincipal;

public class VentanaPrincipal extends JFrame implements Closeable {

  private final JDesktopPane desktopPane;

  /**
   * Constructor por defecto que inicializa la ventana principal.
   */
  public VentanaPrincipal() {
    this.desktopPane = new JDesktopPane();
    /*
     * Se inicializa, se etablecen los parametros basicos de configuracion.
     */
    initialize();
    mostrar();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    setTitle("PICO :: Sistema Contable");
    setName("vPrincipal");
    setBounds(100, 100, 626, 375);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          VentanaPrincipal.this.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        super.windowClosing(e);
      }
    });

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
    btnNewButton.setIcon(new ImageIcon(VentanaPrincipal.class
        .getResource("/org/tango-project/tango-icon-theme/16x16/actions/document-new.png")));
    toolBar.add(btnNewButton);

    getContentPane().add(desktopPane, BorderLayout.CENTER);
    //Agregando el menu principal a la ventana
    setJMenuBar(new MenuPrincipal());
  }

  /**
   * Metodo para mostrar, porque esta es una clase estandart, que no hereda ninguna otra clase,
   * ergo, debemos usar un Wrapper.
   */
  public void mostrar() {
    setVisible(true);
  }

  /**
   * Agrega las ventanas JInternalFrame al escritorio(desktopPane) Centra la ventada en el
   * desktopPane
   *
   * @param ventanaInterna La ventana que se agregara al escritorio, no puede ser null.
   */
  public void agregarAlEscritorio(JInternalFrame ventanaInterna) {
    desktopPane.add(ventanaInterna);
    Dimension dskSize = desktopPane.getSize();
    Dimension frmSize = ventanaInterna.getSize();
    ventanaInterna
        .setLocation((dskSize.width - frmSize.width) / 2, (dskSize.height - frmSize.height) / 2);
    ventanaInterna.setVisible(true);
  }

  @Override
  public void close() throws IOException {
    AplicacionPICO.getINSTANCE().close();
  }
}
