package org.ni.jug.pico.vista.util;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 * Clase JDialogoUtil hereda de JDialog para Dialogos modal
 * la operacion por defecto de cierre DISPOSE_ON_CLOSE, no se puee modificar el tamano
 * @author gacs
 *
 */
public class JDialogoUtil extends JDialog {
	private static final long serialVersionUID = 1L;

	public JDialogoUtil() {
		super(JOptionPane.getRootFrame());
		initJDialogoUtil();
	}
	private void initJDialogoUtil() {
		setVisible(false);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);		

	}
	protected void frmClose() {
		this.dispose();
	}
	
}
