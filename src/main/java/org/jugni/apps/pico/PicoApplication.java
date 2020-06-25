package org.jugni.apps.pico;

import javax.swing.*;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.ui.MainView;
import java.awt.*;
import java.util.logging.Logger;

/**
 * 
 * Hello world!
 *
 */
public class PicoApplication {

  private static PicoApplication application;

  public static PicoApplication getInstance() {
    if (application == null) {
      synchronized (PicoApplication.class) {
        application = new PicoApplication();
      }
    }

    return application;
  }

  private final HibernateHelper hibernateHelper;
  private final MainView rootView;

  public PicoApplication() {
    this.hibernateHelper = new HibernateHelper();
    this.rootView = new MainView(this);
  }

  public HibernateHelper getHibernateHelper() {
    return this.hibernateHelper;
  }

  public MainView getRootView() {
    return this.rootView;
  }

  String m_originalFullName = null;
  private static final Logger fLogger = Logger.getLogger(PicoApplication.class.getName());

  public static void main(String[] args) {
    String osName = System.getProperty("os.name");
    try {
      // itera los LookAndFeels instalados
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {

        // Si se esta en sistema operativo windows
        if (osName != null && osName.contains("Win")) {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          break;
        } else if (info.getClassName().contains("Nimb")) { // otros sistemas operativos
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | javax.swing.UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    }

    PicoApplication.getInstance().initialize(args);
  }

  private void initialize(String[] args) {
    // cargandoSplashScreen
    try {
      showSplashScreen(null);
    } catch (Exception e) {
      e.printStackTrace();
    }

    showMainWindow();
  }

  private void showMainWindow() {
    fLogger.info("Mostrando la Ventana Principal.");
    this.rootView.mostrar();
  }

  private void showSplashScreen(ImageInfo test) throws Exception {
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
    return compare(c1.getRed(), c2.getRed()) && compare(c1.getGreen(), c2.getGreen())
        && compare(c1.getBlue(), c2.getBlue());
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
