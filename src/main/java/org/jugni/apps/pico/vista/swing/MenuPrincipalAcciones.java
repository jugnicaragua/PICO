/*
 * Ejecuta las acciones del menu
 */
package org.jugni.apps.pico.vista.swing;

import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jugni.apps.pico.vista.reportes.Utils;
import org.jugni.apps.pico.vista.swing.dialogos.Acercade;
import org.jugni.apps.pico.vista.swing.dialogos.Respaldo;

/**
 * Clase que ejecuta las intrucciones del menu
 *
 * @author gacs
 *
 */
public class MenuPrincipalAcciones {

    private static final Logger LOGGER = LogManager.getLogger(MenuPrincipalAcciones.class);

     public MenuPrincipalAcciones() {
     }

     public void ejecutarAcercaDe() {
          Acercade about = new Acercade();
          about.setVisible(true);

     }

     public void ejecutarRespaldo() {
          Respaldo respaldo = new Respaldo();
          respaldo.setVisible(true);
     }

     public static void salir() {
          /**
           * Salida el sistema de forma seguro, matar el proceso.
           */
          System.exit(0);
     }

     public static void mostrarVentanaLogin() {
          // new
          // ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/16x16/actions/contact-new.png")));
          // mnArchivo.add(mntmIniciarSession);
          // JMenuItem mntmIniciarSession = new JMenuItem("Iniciar Session");
          // mntmIniciarSession.setIcon(instancia

     }

     public static void mostrarVentanaDatosEmpresa() {
          mostrarVentana(Empresa.getInstancia());
     }

     //Muestra la ventana de formulario catalogo de tipo de cuenta
     public static void mostrarVentanaCuentaTipo() {
          mostrarVentana(CuentaTipoForm.getInstancia());
     }

     //Muestra la ventana de navegacion catalogo de cuenta
     public static void mostrarVentanaCuenta() {
          JInternalFrame w = CuentaNavegaForm.getInstancia();
          if (w!=null) {
               mostrarVentana(w);
          }
     }

     //Muestra la ventana de formulario catalogo de cuenta
     public static void mostrarNuevoVentanaCuenta() {
          mostrarVentana(CuentaForm.getInstancia());
     }

     private static void mostrarVentana(JInternalFrame frm) {
          try {
               //Agrenga  la Ventana al escritorio si la ventana no esta visible
               if (!frm.isVisible()) {
                    VentanaPrincipal.agregarAlEscritorio(frm);
               } else {
                    // si la ventana esta visible pero debajo de otras ventanas se mueve al frente y se selecciona
                    frm.moveToFront();
                    if (!frm.isSelected()) {
                         frm.setSelected(true);
                    }
               }

          } catch (PropertyVetoException e) {
               LOGGER.error("Error al activar la ventana Tipo de cuenta", e);
          }

     }

     public static void mostrarParametrosGenerales() {

     }

     //muestra el reporte del balance general
     public static void mostrarBalanceGeneral() {
          try {
               Utils.showReporte("BalanceGeneral.jrxml");
          } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }
     }

}
