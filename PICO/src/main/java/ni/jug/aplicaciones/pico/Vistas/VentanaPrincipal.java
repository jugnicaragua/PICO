package ni.jug.aplicaciones.pico.Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JProgressBar;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class VentanaPrincipal {

	private JFrame frmPicoSistema;
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
		frmPicoSistema = new JFrame();
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
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmPicoSistema.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmPicoSistema.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmIniciarSession = new JMenuItem("Iniciar Session");
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
		
		JMenu mnHerramientas = new JMenu("Herramientas");
		menuBar.add(mnHerramientas);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mnHerramientas.add(mntmConfiguracion);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmBalanceGeneral = new JMenuItem("Balance General");
		mnReportes.add(mntmBalanceGeneral);
		
		JMenuItem mntmEstadoDeResultado = new JMenuItem("Estado de Resultado");
		mnReportes.add(mntmEstadoDeResultado);
		
		JMenuItem mntmBalanzaDeComprobacion = new JMenuItem("Balanza de Comprobacion");
		mnReportes.add(mntmBalanzaDeComprobacion);
		
		JMenu mnGraficos = new JMenu("Graficos");
		mnReportes.add(mnGraficos);
		
		JMenuItem mntmProfit = new JMenuItem("profit");
		mnGraficos.add(mntmProfit);
		
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
		
		JMenuItem mntmVerCodigoFuente = new JMenuItem("Ver codigo fuente");
		mnAcercaDe.add(mntmVerCodigoFuente);
		
		JMenuItem mntmAutores = new JMenuItem("Autores");
		mnAcercaDe.add(mntmAutores);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
