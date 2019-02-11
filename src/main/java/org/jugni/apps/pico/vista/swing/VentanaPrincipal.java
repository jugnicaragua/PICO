package org.jugni.apps.pico.vista.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;


public class VentanaPrincipal {

	private final static JFrame frmPicoSistema = new JFrame(); ;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmPicoSistema.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
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
		
//		MenuPrincipal menuBar = new MenuPrincipal();
		frmPicoSistema.setJMenuBar(new MenuPrincipal());
		
	}
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	
	
	public void show() {
		
		frmPicoSistema.setVisible(true);
	}
}
