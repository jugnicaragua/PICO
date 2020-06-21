package org.ni.jug.pico;

import org.ni.jug.pico.ui.core.VentanaPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class PicoSwingRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PicoSwingRunner.class);

    @Override
    public void run(String... args) {
        PicoSwingRunner.initialize(args);
    }

    private static void initialize(String[] args) {
        UIManager.LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lookAndFeels.length; i ++) {
            UIManager.LookAndFeelInfo info = lookAndFeels[i];
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception exc) {
                    LOGGER.error("No se pudo aplicar el tema Nimbus para la interfaz grafica", exc);
                }
            }
        }
        try {
            showSplashScreen(null);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error con el splash", e);
        }

        showMainWindow();
    }

    private static void showMainWindow() {
        LOGGER.info("Mostrando la Ventana Principal.");
        VentanaPrincipal mainWindow = new VentanaPrincipal();
        mainWindow.mostrar();
    }

    private static void showSplashScreen(ImageInfo test) throws AWTException, RuntimeException {
        LOGGER.info("Showing the splash screen.");
        SplashScreen splashScreen = SplashScreen.getSplashScreen();
        if (splashScreen == null) {
            throw new RuntimeException("Splash screen is not shown!");
        }
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
