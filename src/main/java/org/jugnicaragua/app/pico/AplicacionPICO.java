package org.jugnicaragua.app.pico;

import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jugnicaragua.app.pico.vista.VentanaPrincipal;

public class AplicacionPICO {

  private static AplicacionPICO INSTANCE;

  public static AplicacionPICO getINSTANCE() {
    return INSTANCE;
  }

  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }

    INSTANCE = new AplicacionPICO();
  }

  private final Logger LOG = Logger.getLogger(AplicacionPICO.class.getName());
  private final VentanaPrincipal ventanaPrincipal;

  public AplicacionPICO() {
    ventanaPrincipal = initialize();
  }

  private VentanaPrincipal initialize() {
    return showMainWindow();
  }

  private VentanaPrincipal showMainWindow() {
    LOG.info("Mostrando la Ventana Principal.");
    return new VentanaPrincipal();
  }

  public VentanaPrincipal getVentanaPrincipal() {
    return ventanaPrincipal;
  }
}