package org.jugnicaragua.app.pico;

import org.jugnicaragua.app.pico.vista.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * 
 * Hello world!
 *
 */
public class MiAplicacionPICO {

  String m_originalFullName = null;

  // PRIVATE

  private static SplashScreen fSplashScreen;
  private static final Logger fLogger = Logger.getLogger(MiAplicacionPICO.class.getName());
  private static final String SPLASH_IMAGE = "StocksMonitor.gif";

  // main
  public static void main(String[] args) {

    System.out.println("Hello World!");

    String osName = System.getProperty("os.name");
          try {
               //itera los LookAndFeels instalados
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                         //"GTK+"
                         //"CDE/Motif"
                         // "Nimbus"
                         // "Metal"
                         //Si se esta en sistema operativo windows  
                    if (osName != null && osName.contains("Win") ) {
                         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                         break;
                    } else if (info.getClassName().contains("Nimb")) { //otros sistemas operativos
                         UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
               ex.printStackTrace();
          }

   /* if (osName != null && osName.indexOf("Windows") != -1) {
      try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (UnsupportedLookAndFeelException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        // comentaod por ahora TODO encontrar el NAtivo
        // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        /*
         * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace(); }
         * catch (InstantiationException e) { // TODO Auto-generated catch block e.printStackTrace(); }
         * catch (IllegalAccessException e) { // TODO Auto-generated catch block e.printStackTrace();
         * 
         * 
         * } catch (UnsupportedLookAndFeelException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
*/

    // TODO: Verificacion del sistema

    // TODO: cargar preferencias

    // TODO: Validar cosas de la base de datos.

    // si todo bien,

    MiAplicacionPICO.initialize(args);

    //
  }

  private static void initialize(String[] args) {
    // cargandoSplashScreen
    try {
      showSplashScreen(null);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    showMainWindow();

    // EventQueue.invokeLater(new SplashScreenCloser());
  }

  private static void showMainWindow() {
    fLogger.info("Mostrando la Ventana Principal.");
    VentanaPrincipal mainWindow = new VentanaPrincipal();
    mainWindow.mostrar();
  }

  private static void showSplashScreen(ImageInfo test) throws Exception {
    fLogger.info("Showing the splash screen.");
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen == null) {
      throw new RuntimeException("Splash screen is not shown!");
    }
    Graphics2D g = splashScreen.createGraphics();
    Rectangle splashBounds = splashScreen.getBounds();
    int screenX = (int) splashBounds.getCenterX();
    int screenY = (int) splashBounds.getCenterY();
    Robot robot = new Robot();
    Color splashScreenColor = robot.getPixelColor(screenX, screenY);

    float scaleFactor = getScaleFactor();
    Color testColor = (1 < scaleFactor) ? test.color2x : test.color1x;
    if (!compare(testColor, splashScreenColor)) {
      throw new RuntimeException("Image with wrong resolution is used for splash screen!");
    }
  }

  void exitFromSystem() {
       System.gc();
    System.exit(0);
  }

  private int getLastIndexOfSlash(String thisname) {
    int index = m_originalFullName.lastIndexOf("/");
    if (index == -1)
      index = m_originalFullName.lastIndexOf("\\");
    return index;
  }

  /**
   * Removes the splash screen.
   *
   * Invoke this <tt>Runnable</tt> using <tt>EventQueue.invokeLater</tt>, in order to remove the
   * splash screen in a thread-safe manner.
   */
  private static final class SplashScreenCloser implements Runnable {
    @Override
    public void run() {
      fLogger.fine("Closing the splash screen.'");
    }
  }

  private static void logBasicSystemInfo() {
    fLogger.info("Launching the application...");
    fLogger.config("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
    fLogger.config("JRE: " + System.getProperty("java.version"));
    fLogger.info("Java Launched From: " + System.getProperty("java.home"));
    fLogger.config("Class Path: " + System.getProperty("java.class.path"));
    fLogger.config("Library Path: " + System.getProperty("java.library.path"));
    // fLogger.config("Application Name: " + Const.APP_NAME + "/" + Consts.APP_VERSION);
    fLogger.config("User Home Directory: " + System.getProperty("user.home"));
    fLogger.config("User Working Directory: " + System.getProperty("user.dir"));
    fLogger.info("Test INFO logging.");
    fLogger.fine("Test FINE logging.");
    fLogger.finest("Test FINEST logging.");
  }

  static float getScaleFactor() {

    final Dialog dialog = new Dialog((Window) null);
    dialog.setSize(100, 100);
    dialog.setModal(true);
    float[] scaleFactors = new float[1];
    Panel panel = new Panel() {

      @Override
      public void paint(Graphics g) {
        String scaleStr = System.getenv("GDK_SCALE");
        if (scaleStr != null && !scaleStr.equals("")) {
          try {
            scaleFactors[0] = Float.valueOf(scaleStr);
          } catch (NumberFormatException ex) {
            scaleFactors[0] = 1.0f;
          }
        }
        dialog.setVisible(false);
      }
    };
    dialog.add(panel);
    dialog.setVisible(true);
    dialog.dispose();
    return scaleFactors[0];
  }

  static boolean compare(Color c1, Color c2) {
    return compare(c1.getRed(), c2.getRed()) && compare(c1.getGreen(), c2.getGreen()) && compare(c1.getBlue(), c2.getBlue());
  }

  static boolean compare(int n, int m) {
    return Math.abs(n - m) <= 50;
  }

  static class ImageInfo {

    final String name1x;
    final String name2x;
    final Color color1x;
    final Color color2x;

    public ImageInfo(String name1x, String name2x, Color color1x, Color color2x) {
      this.name1x = name1x;
      this.name2x = name2x;
      this.color1x = color1x;
      this.color2x = color2x;
    }
  }
}
