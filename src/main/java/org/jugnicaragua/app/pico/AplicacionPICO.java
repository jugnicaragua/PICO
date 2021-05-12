package org.jugnicaragua.app.pico;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.SessionFactory;
import org.jugnicaragua.app.pico.configuraciones.HibernateConfiguracion;
import org.jugnicaragua.app.pico.vista.VentanaPrincipal;

public class AplicacionPICO implements Closeable {

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
  private final HibernateConfiguracion hibernateConfiguracion;

  public AplicacionPICO() {
    this.hibernateConfiguracion = new HibernateConfiguracion();
    ventanaPrincipal = initialize();

  }

  public SessionFactory getDatabaseSession() {
    return this.hibernateConfiguracion.getSessionFactory();
  }

  private VentanaPrincipal initialize() {
    // Se inicializa la base de datos.
    this.hibernateConfiguracion
        .cargar();

    // Se inicializa la parte grafica.
    return showMainWindow();
  }

  private VentanaPrincipal showMainWindow() {
    LOG.info("Mostrando la Ventana Principal.");
    return new VentanaPrincipal();
  }

  public VentanaPrincipal getVentanaPrincipal() {
    return ventanaPrincipal;
  }

  @Override
  public void close() throws IOException {
    LOG.info("Hasta luego!!");
    if (this.hibernateConfiguracion != null) {
      this.hibernateConfiguracion.close();
    }
  }
}