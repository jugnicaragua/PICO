package ni.jug.aplicaciones.pico.Vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyEvent;

public class Empresa extends JInternalFrame {
	private JTable table;
	private JTextField txtXx;
	private JTextField txtD;
	private JTextField txtD_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empresa frame = new Empresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Empresa() {
		setToolTipText("Datos de la empresa");
		setOpaque(true);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setIconifiable(true);
		setTitle("Datos de la empresa");
		setClosable(true);
		setBounds(100, 100, 493, 402);
		getContentPane().setLayout(null);
		
		JLabel lblMiEmpresa = new JLabel("Mi empresa");
		lblMiEmpresa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMiEmpresa.setBounds(10, 11, 143, 29);
		getContentPane().add(lblMiEmpresa);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 51, 369, 195);
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[140.00px][123px,grow][123px]", "[35.00px][][][]"));
		
		JLabel lblRuc = new JLabel("RUC:");
		panel.add(lblRuc, "cell 0 0,growx,aligny center");
		
		txtXx = new JTextField();
		txtXx.setText("xx");
		panel.add(txtXx, "cell 1 0,grow");
		txtXx.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel, "cell 0 1,grow");
		
		JLabel lblRepresentanteLegal = new JLabel("Representante Legal");
		panel.add(lblRepresentanteLegal, "cell 0 2,alignx left");
		
		txtD = new JTextField();
		txtD.setText("d");
		panel.add(txtD, "cell 1 2,growx");
		txtD.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblDireccion, "cell 0 3,alignx left");
		
		txtD_1 = new JTextField();
		txtD_1.setText("d");
		panel.add(txtD_1, "cell 1 3,growx");
		txtD_1.setColumns(10);
		
		table = new JTable();
		table.setBounds(394, 338, -225, -94);
		getContentPane().add(table);
		
		JButton btncerrar = new JButton("Cerrar");
		btncerrar.setMnemonic(KeyEvent.VK_C);
		btncerrar.setBounds(392, 340, 89, 23);
		getContentPane().add(btncerrar);

	}
}
