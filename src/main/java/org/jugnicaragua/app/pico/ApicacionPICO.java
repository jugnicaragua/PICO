package org.jugnicaragua.app.pico;

import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jugnicaragua.app.pico.vista.VentanaPrincipal;

public class ApicacionPICO {

  private final Logger LOG = Logger.getLogger(ApicacionPICO.class.getName());

  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }

    var instance = new ApicacionPICO();
    instance.initialize();
  }

  private void initialize() {
    showMainWindow();
  }

  private void showMainWindow() {
    LOG.info("Mostrando la Ventana Principal.");
    new VentanaPrincipal()
        .mostrar();
  }
}