package org.jugni.apps.pico.vista.swing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;


public class VentanaPrincipal {

	private final static JFrame frmPicoSistema = new JFrame(); ;

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		/**
		 * Se inicializa,
		 * se etablecen los parametros basicos de configuracion.
		 */
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPicoSistema.setTitle("PICO :: Sistema Contable");
		frmPicoSistema.setName("vPrincipal");
		frmPicoSistema.setBounds(100, 100, 626, 375);
		frmPicoSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar_1 = new JToolBar();
		frmPicoSistema.getContentPane().add(toolBar_1, BorderLayout.SOUTH);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar_1.add(lblFecha);
		
		JLabel lblPeriodoFiscal = new JLabel("Periodo Fiscal");
		toolBar_1.add(lblPeriodoFiscal);
		
		JLabel lblNewLabel = new JLabel("New label");
		toolBar_1.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		toolBar_1.add(progressBar);
		
		JToolBar toolBar = new JToolBar();
		frmPicoSistema.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Nuevo CD");
		btnNewButton.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/16x16/actions/document-new.png")));
		toolBar.add(btnNewButton);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmPicoSistema.getContentPane().add(desktopPane, BorderLayout.CENTER);

		//Agregando el menu principal a la ventana
		frmPicoSistema.setJMenuBar(new MenuPrincipal());
	}


	/**
	 * Metodo para mostrar, porque esta es una clase estandart, que no hereda ninguna otra clase, ergo, debemos usar un Wrapper.
	 */
	public void mostrar() {
		frmPicoSistema.setVisible(true);
	}
}
