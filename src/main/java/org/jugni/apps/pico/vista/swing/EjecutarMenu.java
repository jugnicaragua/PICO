/*
 * Ejecuta las acciones del menu
 */
package org.jugni.apps.pico.vista.swing;

import java.awt.Frame;


import org.jugni.apps.pico.vista.swing.dialogos.Acercade;

/**
 * Clase que ejecuta las intrucciones del menu
 * @author gacs
 *
 */
public class EjecutarMenu {
	private final Frame parent ; //hijo del frame principal de la aplicacion
	public EjecutarMenu(Frame parent) {
		this.parent=parent;
	}
	
	public void ejecutarAcercaDe() {
		Acercade about =new  Acercade(parent);
		about.setVisible(true);
		
	}

}
