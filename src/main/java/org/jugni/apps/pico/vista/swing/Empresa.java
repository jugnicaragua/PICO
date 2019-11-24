package org.jugni.apps.pico.vista.swing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Empresa extends JInternalFrame {
  private JTextField txtXx;
  private JTextField txtD;
  private JTextField txtD_1;

  // Area de definicion de Variables Globales
  static Empresa miInstancia;


  /**
   * Constructor de Empresa
   */
  public Empresa() {
    // establciendo el valor del Sigleton
    miInstancia = this;

    setToolTipText("Datos de la empresa");
    setOpaque(true);
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setIconifiable(false);
    setTitle("Datos de la empresa");
    setClosable(true);
    setBounds(100, 100, 568, 339);
    // getContentPane().setLayout(null);

    JLabel lblMiEmpresa = new JLabel("Mi empresa");
    lblMiEmpresa.setFont(new Font("Tahoma", Font.BOLD, 22));
    lblMiEmpresa.setBounds(10, 11, 143, 29);
    getContentPane().add(lblMiEmpresa, BorderLayout.NORTH);

    JPanel panel = new JPanel();
    panel.setBounds(41, 51, 410, 204);
    getContentPane().add(panel, BorderLayout.CENTER);
    panel.setLayout(new FormLayout(new ColumnSpec[] {
        FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
        ColumnSpec.decode("142px"),
        FormSpecs.UNRELATED_GAP_COLSPEC,
        ColumnSpec.decode("100px"),},
      new RowSpec[] {
        FormSpecs.RELATED_GAP_ROWSPEC,
        RowSpec.decode("19px"),
        FormSpecs.RELATED_GAP_ROWSPEC,
        RowSpec.decode("19px"),
        FormSpecs.RELATED_GAP_ROWSPEC,
        RowSpec.decode("19px"),
        FormSpecs.RELATED_GAP_ROWSPEC,
        RowSpec.decode("15px"),}));
    // panel.setLayout(new MigLayout("", "[140.00px][123px,grow][123px]", "[35.00px][][][]"));
    // panel.setLayout(new Layout("", "[140.00px][123px,grow][123px]", "[35.00px][][][]"));

    JLabel lblRuc = new JLabel("RUC:");
    panel.add(lblRuc, "2, 2, left, center");

    txtXx = new JTextField();
    txtXx.setText("xx");
    panel.add(txtXx, "4, 2, left, top");
    txtXx.setColumns(10);

    JLabel lblRepresentanteLegal = new JLabel("Representante Legal");
    panel.add(lblRepresentanteLegal, "2, 4, left, center");

    txtD = new JTextField();
    txtD.setText("d");
    panel.add(txtD, "4, 4, right, top");
    txtD.setColumns(10);

    JLabel lblDireccion = new JLabel("Direccion");
    lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblDireccion, "2, 6, left, center");

    txtD_1 = new JTextField();
    txtD_1.setText("d");
    panel.add(txtD_1, "4, 6, left, top");
    txtD_1.setColumns(10);

    JLabel lblNewLabel = new JLabel("New label");
    panel.add(lblNewLabel, "2, 8, left, center");

    JPanel panel_1 = new JPanel();
    getContentPane().add(panel_1, BorderLayout.SOUTH);

    JButton btnCerrar = new JButton("Cerrrar");
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        mtdCerrar();
      }
    });
    panel_1.add(btnCerrar);

    // establciendo el valor del Sigleton
    miInstancia = this;

  }

  protected void mtdCerrar() {
    this.dispose();
  }

  /**
   * SingleTon de Empresa.
   *
   * @return
   */
  public static Empresa getInstancia() {
    return null == miInstancia ? (new Empresa()) : miInstancia;
  }
}
