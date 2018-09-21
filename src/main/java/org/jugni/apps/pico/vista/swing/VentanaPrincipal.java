package org.jugni.apps.pico.vista.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import org.jugni.apps.pico.vista.reportes.Utils;


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
		
		JMenuBar menuBar = new JMenuBar();
		frmPicoSistema.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmIniciarSession = new JMenuItem("Iniciar Session");
		mntmIniciarSession.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/16x16/actions/contact-new.png")));
		mnArchivo.add(mntmIniciarSession);
		
		JMenuItem mntmEmpresa = new JMenuItem("Empresa");
		mntmEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//creando instancia del nuevo formulario.
				Empresa empresa = new Empresa();
				empresa.setVisible(true);
				empresa.setOpaque(true);
				frmPicoSistema.getContentPane().add(empresa);
			}
		});
		mnArchivo.add(mntmEmpresa);
		
		JMenuItem mntmParametrosGenerales = new JMenuItem("Parametros Generales");
		mnArchivo.add(mntmParametrosGenerales);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ciclo Fiscal");
		mnArchivo.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmSalir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/16x16/actions/system-log-out.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//matar todo el prceso
				System.exit(0);;
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnContabilidad = new JMenu("Contabilidad");
		menuBar.add(mnContabilidad);
		
		JMenuItem mntmComprobanteDeDiarios = new JMenuItem("Comprobante de  Diarios");
		mnContabilidad.add(mntmComprobanteDeDiarios);
		
		JMenuItem mntmCatalogoContable = new JMenuItem("Catalogo Contable");
		mnContabilidad.add(mntmCatalogoContable);
		
		JMenu mnCxc = new JMenu("CxC");
		menuBar.add(mnCxc);
		
		JMenu mnCxp = new JMenu("CxP");
		menuBar.add(mnCxp);
		
		JMenu mnInventario = new JMenu("Inventario");
		menuBar.add(mnInventario);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		mnEmpleados.add(mnNuevo);
		
		JMenu mnProduccion = new JMenu("Produccion");
		menuBar.add(mnProduccion);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/net/sf/jasperreports/engine/images/chart-16.png")));
		mnReportes.setSelectedIcon(new ImageIcon(VentanaPrincipal.class.getResource("/org/tango-project/tango-icon-theme/22x22/actions/window-new.png")));
		mnReportes.setMnemonic('R');
		menuBar.add(mnReportes);
		
		JMenuItem mntmBalanceGeneral = new JMenuItem("Balance General");
		mntmBalanceGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Utils.showReporte( "BalanceGeneral.jrxml" );
					Utils.showReporte( "BalanceGeneral.jrxml" );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmBalanceGeneral.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/net/sf/jasperreports/engine/images/subreport-16.png")));
		mnReportes.add(mntmBalanceGeneral);
		
		JMenuItem mntmEstadoDeResultado = new JMenuItem("Estado de Resultado");
		mnReportes.add(mntmEstadoDeResultado);
		
		JMenuItem mntmBalanzaDeComprobacion = new JMenuItem("Balanza de Comprobacion");
		mnReportes.add(mntmBalanzaDeComprobacion);
		
		JMenu mnGraficos = new JMenu("Graficos");
		mnReportes.add(mnGraficos);
		
		JMenuItem mntmProfit = new JMenuItem("profit");
		mnGraficos.add(mntmProfit);
		
		JMenu mnHerramientas = new JMenu("Herramientas");
		menuBar.add(mnHerramientas);
		
		JMenuItem mntmCalularAguinaldo = new JMenuItem("Calular Aguinaldo");
		mnHerramientas.add(mntmCalularAguinaldo);
		
		JSeparator separator_1 = new JSeparator();
		mnHerramientas.add(separator_1);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mnHerramientas.add(mntmConfiguracion);
		
		JMenu mnVentanas = new JMenu("Ventanas");
		menuBar.add(mnVentanas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmManualEnLina = new JMenuItem("Manual en lina");
		mnAyuda.add(mntmManualEnLina);
		
		JMenuItem mntmIrAlForo = new JMenuItem("Ir al Foro");
		mnAyuda.add(mntmIrAlForo);
		
		JMenu mnAcercaDe = new JMenu("Acerca de ");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmEnviarUnComentario = new JMenuItem("Enviar un comentario");
		mnAcercaDe.add(mntmEnviarUnComentario);
		
		JMenuItem mntmPico = new JMenuItem("PICO");
		mnAcercaDe.add(mntmPico);
		
		JMenuItem mntmLicencias = new JMenuItem("Licencias");
		mnAcercaDe.add(mntmLicencias);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnAcercaDe.add(mntmAcercaDe);
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
