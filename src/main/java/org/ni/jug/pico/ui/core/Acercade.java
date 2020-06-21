package org.ni.jug.pico.ui.core;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import org.ni.jug.pico.ui.util.JDialogoUtil;

/**
 * Acerca de PICO
 *
 * @author gacs
 *
 */
public class Acercade extends JDialogoUtil {

     private JLabel lblTitle;
     private JLabel lblPico;
     private JLabel lblImagenPico;
     private JPanel pnlButton;
     private JPanel contentPanel;
     private JPanel pnlTitulo;
     private JEditorPane txtPanel;
     private JScrollPane jsPanel;
     private JButton cmdOk;

     /**
      * Crontuctor Acercade
      *
      * @param parent recibe el frame del cual es jdiogo es hijo
      *
      */
     public Acercade() {
          initAcercade();
          pack();
          setLocationRelativeTo(null);

     }

     /*	
	public static void main(String[] args) {
		try {
			Acercade dialog = new Acercade();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
      */
     private void initAcercade() {
          //Inicializa el Jdialogo
          {
               setTitle("Acerca de");
               setName("Acercade");
               getContentPane().setLayout(new BorderLayout());

          }
          //Se instancian los componentes del jdialogo
          {
               ImageIcon icon = new ImageIcon(getClass().getResource("/jugnica.jpg"));
               lblImagenPico = new JLabel(icon);
               lblTitle = new JLabel("Sistema contable");
               lblPico = new JLabel("PICO ");
               contentPanel = new JPanel();
               pnlButton = new JPanel();
               pnlTitulo = new JPanel();
               txtPanel = new JEditorPane();
               jsPanel = new JScrollPane(txtPanel);
               cmdOk = new JButton("Aceptar");
          }
          //inicializa los paneles
          {
               contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
               contentPanel.setLayout(new FlowLayout());
               contentPanel.add(lblImagenPico);
               contentPanel.add(jsPanel);
               pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
               pnlButton.add(cmdOk);
               pnlTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
               pnlTitulo.add(lblPico);
               pnlTitulo.add(lblTitle);

               getContentPane().add(contentPanel, BorderLayout.CENTER);
               getContentPane().add(pnlButton, BorderLayout.SOUTH);
               getContentPane().add(pnlTitulo, BorderLayout.NORTH);
          }
          //Se inicisilizan los componentes
          {
               lblPico.setFont(new Font("Dialog", Font.BOLD, 24));
               lblTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
               jsPanel.setPreferredSize(new Dimension(370, 300));
               jsPanel.setHorizontalScrollBarPolicy(
                       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
               jsPanel.setVerticalScrollBarPolicy(
                       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
               //Cuando se preciona un hyperlink llama a la palicacion predeterminanda en el sistema operativo
               txtPanel.addHyperlinkListener(e -> {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                         if (Desktop.isDesktopSupported()) {
                              try {
                                   Desktop.getDesktop().browse(e.getURL().toURI());
                              } catch (IOException | URISyntaxException e1) {
                                   e1.printStackTrace();
                              }
                         }
                    }
               });
               txtPanel.setEditable(false);
               txtPanel.setContentType("text/html");
               txtPanel.setText(
                       "<STROM><B>PICO </B><STROM>" + "-Sistema de contable" + "<BR>"
                       + "<B>Version    :</B>" + "2019-05" + "<BR>"
                       + "<B>Licencia   :</B>" + "GPLv3" + "<BR>"
                       + "<BR>"
                       + "<B>Usuario    :</B>" + System.getProperty("user.name") + "<BR>"
                       + "<B>Directorio :</B>" + System.getProperty("user.home") + "<BR>"
                       + "<B>SO            :</B>" + System.getProperty("os.name") + "-" + System.getProperty("os.arch") + "<BR>"
                       + "<B>Java          :</B>" + System.getProperty("java.vendor") + "-" + System.getProperty("java.version") + "<BR>"
                       + "<B>From:</B> " + System.getProperty("java.home")
                       + " <a href=\" " + System.getProperty("java.vendor.url")
                       + "\" >" + System.getProperty("java.vendor.url") + "</a> <BR><BR>"
                       + "Grupo de Usuarios Java de Nicaragua." + "<BR>"
                       + "<BR>"
                       + " ====== Autores y Colaboradores ====== <BR>"
                       + "Gustavo Castro " + "<a href=\"mailto:gacsnic75@gmail.com\"> gacsnic75@gmail.com  </a> " + "<BR>"
                       + "perencejo " + "<a href=\"mailto:ffff@gmail.com\"> perencejo  </a> " + "<BR>"
                       + "perencejo " + "<a href=\"mailto:ffff@gmail.com\"> perencejo  </a> " + "<BR>"
                       + "perencejo " + "<a href=\"mailto:ffff@gmail.com\"> perencejo  </a> " + "<BR>"
                       + "perencejo " + "<a href=\"mailto:ffff@gmail.com\"> perencejo  </a> " + "<BR>"
                       + "<BR>"
               );

               cmdOk.setActionCommand("OK");

               cmdOk.addActionListener(a -> {
                    frmClose();
               });
               pnlButton.add(cmdOk);
               getRootPane().setDefaultButton(cmdOk);
          }
     }

}
