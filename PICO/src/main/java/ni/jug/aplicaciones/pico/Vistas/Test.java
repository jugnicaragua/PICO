package ni.jug.aplicaciones.pico.Vistas;

import java.awt.BorderLayout;

import javax.swing.DefaultDesktopManager;
import javax.swing.DesktopManager;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class Test extends JFrame {

	JDesktopPane desktop;
	DesktopManager manager;

	public Test() {
		super("Internal Frames Demo");
		desktop = new JDesktopPane();
		desktop.setOpaque(false);
		getContentPane().add(desktop, BorderLayout.CENTER);

		JInternalFrame internal;
		JButton button;

		internal = new JInternalFrame("Always Below", true, true, true, true);
		button = new JButton("Ok");
		internal.getContentPane().add(button, BorderLayout.CENTER);
		internal.setBounds(0, 0, 200, 75);
		desktop.add(internal, new Integer(desktop.DEFAULT_LAYER.intValue() - 1));

		internal = new JInternalFrame("Default Layer #1", true, true, true, true);
		button = new JButton("Ok");
		internal.getContentPane().add(button, BorderLayout.CENTER);
		internal.setBounds(25, 25, 200, 75);
		desktop.add(internal, desktop.DEFAULT_LAYER);

		internal = new JInternalFrame("Default Layer #2", true, false, true, true);
		button = new JButton("Ok");
		internal.getContentPane().add(button, BorderLayout.CENTER);
		internal.setBounds(50, 50, 200, 75);
		desktop.add(internal, desktop.DEFAULT_LAYER);

		internal = new JInternalFrame("Always Above", true, false, true, true);
		button = new JButton("Ok");
		internal.getContentPane().add(button, BorderLayout.CENTER);
		internal.setBounds(75, 75, 200, 75);

		desktop.add(internal, new Integer(desktop.DEFAULT_LAYER.intValue() + 1));

		setSize(300, 300);
		show();
	}

	public static void main(String args[]) {
		new Test();
	}
}
