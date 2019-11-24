/*
 * Ejecuta las acciones del menu
 */
package org.jugni.apps.pico.vista.swing;

import org.jugni.apps.pico.vista.swing.dialogos.Acercade;
import org.jugni.apps.pico.vista.swing.dialogos.Respaldo;

/**
 * Clase que ejecuta las intrucciones del menu
 * @author gacs
 *
 */
public class MenuPrincipalAcciones {
	public MenuPrincipalAcciones() {
	}
	
	public void ejecutarAcercaDe() {
		Acercade about =new  Acercade();
		about.setVisible(true);
		
	}
	public void ejecutarRespaldo() {
		Respaldo respaldo =new  Respaldo();
		respaldo.setVisible(true);
	}

	public static void salir() {
		/**
		 * Salida el sistema de forma seguro, matar el proceso.
		 */
		System.exit(0);
	}

	public static void mostrarVentanaLogin() {
		//new ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/16x16/actions/contact-new.png")));
		//mnArchivo.add(mntmIniciarSession);
		//JMenuItem mntmIniciarSession = new JMenuItem("Iniciar Session");
		//mntmIniciarSession.setIcon(

	}

	public static void mostrarVentanaDatosEmpresa() {
		/**
		 * Llamando un Singleton de Ventana Empresa
		 */
		Empresa.getInstancia().setVisible(true);

	}

	public static void mostrarParametrosGenerales() {

	}

}
