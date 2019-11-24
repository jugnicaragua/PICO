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
public class EjecutarMenu {
	public EjecutarMenu() {
	}
	
	public void ejecutarAcercaDe() {
		Acercade about =new  Acercade();
		about.setVisible(true);
		
	}
	public void ejecutarRespaldo() {
		Respaldo respaldo =new  Respaldo();
		respaldo.setVisible(true);
		
	}

}
