package org.jugni.apps.pico.ui.catalogue;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.data.dao.EmpresaDao;
import org.jugni.apps.pico.security.Rol;
import org.jugni.apps.pico.security.annotation.Access;
import org.jugni.apps.pico.ui.util.BaseInternalView;
import org.jugni.apps.pico.ui.util.Utilities;
import org.jugni.apps.pico.data.model.Empresa;

@SuppressWarnings("serial")
@Access(name = "registro-empresa", rol = Rol.ADMIN)
public class EmpresaView extends BaseInternalView {

  private final EmpresaDao empresaDao;

  public EmpresaView(PicoApplication application) {
    super(application);
    this.empresaDao = new EmpresaDao(getHibernateHelper().getSessionFactory());
  }

  private JTextField txtRuc;
  private JTextField txtRazonSocial;
  private JTextField txtEmitirA;
  private JTextField txtDireccion;
  private JTextField txtRepresentanteLegal;
  private JTextField txtContacto;
  private JTextField txtWebSite;
  private JTextField txtCorreo;
  private JTextField txtTelefono;
  private JLabel lblImagen;
  private Empresa datosEmpresa;
  final String IMAGENPATH = System.getProperty("user.dir") + "/var/db/logo.png";
  JButton btnGuardar = new JButton("Guardar");
  ImageIcon imgLogo;
  // Area de definicion de Variables Globales
  static EmpresaView miInstancia;

  private void initEmpresa() {
    setToolTipText("Datos de la empresa");
    setOpaque(true);
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setIconifiable(false);
    setTitle("Datos de la empresa");
    setClosable(true);
    Color bgCampoObligatorio = new Color(255, 228, 181); // Color de fondo para los campos que son
                                                         // obligatorios

    // getContentPane().setLayout(null);
    // Se establece las teclas que al presionar avance el foco
    Set<AWTKeyStroke> keyFoco = new HashSet<>();
    keyFoco.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
    keyFoco.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, 0));
    getContentPane().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keyFoco);

    JLabel lblTitle = new JLabel("Mi empresa");
    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
    lblTitle.setForeground(Color.WHITE);
    lblTitle.setPreferredSize(new Dimension(40, 40));
    JPanel pnlTitle = new JPanel(); // Panel que contiene el titulo del formulario
    JPanel panel = new JPanel();
    JPanel pnlEmpresa = new JPanel();
    JPanel pnlContacto = new JPanel();
    JPanel imagenPanel = new JPanel();
    JPanel panel_button = new JPanel();

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    pnlEmpresa.setAlignmentX(LEFT_ALIGNMENT);
    pnlContacto.setAlignmentX(LEFT_ALIGNMENT);
    // Agrega bordes con titulos a los paneles pnlEmpresa y pnlContacto
    pnlEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empresa",
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));
    pnlContacto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacto",
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));
    panel.add(pnlEmpresa);
    panel.add(Box.createRigidArea(new Dimension(10, 10)));
    panel.add(pnlContacto);
    imagenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    Box boxImagen = Box.createVerticalBox();
    JButton btnImagen = new JButton("Imagen");
    btnImagen.addActionListener((ActionEvent arg0) -> {
      btnGuardar.setEnabled(true);
      obtenerImagen();
    });
    btnImagen.setAlignmentX(CENTER_ALIGNMENT);
    btnImagen.setFocusable(false);
    // verifica que la imagen del logo existe
    if (new File(IMAGENPATH).exists()) {
      imgLogo = new javax.swing.ImageIcon(IMAGENPATH);
    } else {
      imgLogo = new javax.swing.ImageIcon(getClass().getResource("/jugnica.jpg"));
    }
    lblImagen = new JLabel(imgLogo);
    lblImagen.setPreferredSize(new Dimension(20, 100));
    lblImagen.setAlignmentX(CENTER_ALIGNMENT);
    boxImagen.add(lblImagen);
    boxImagen.add(Box.createRigidArea(new Dimension(10, 10)));
    boxImagen.add(btnImagen);
    imagenPanel.add(boxImagen);

    // panel.setBounds(41, 51, 410, 204);
    pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.LINE_AXIS));
    pnlTitle.setBackground(new Color(0, 117, 175));
    pnlTitle.add(Box.createRigidArea(new Dimension(10, 10)));
    pnlTitle.add(lblTitle);
    getContentPane().add(pnlTitle, BorderLayout.NORTH);
    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(panel_button, BorderLayout.SOUTH);
    getContentPane().add(imagenPanel, BorderLayout.EAST);

    pnlEmpresa.setLayout(new FormLayout(
        new ColumnSpec[] {FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("156px"),
            FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("300px"),},
        new RowSpec[] {FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),
            FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),}));

    pnlContacto.setLayout(new FormLayout(
        new ColumnSpec[] {FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("156px"),
            FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("300px"),},
        new RowSpec[] {FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),
            FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),
            FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),}));

    // panel.setLayout(new MigLayout("", "[140.00px][123px,grow][123px]", "[35.00px][][][]"));
    // panel.setLayout(new Layout("", "[140.00px][123px,grow][123px]", "[35.00px][][][]"));
    JLabel lblRuc = new JLabel("N°. RUC. :");
    lblRuc.setToolTipText("Ingrese el numero RUC.");
    pnlEmpresa.add(lblRuc, "2, 2, left, center");

    JLabel lblRazonSocial = new JLabel("Razón Social :");
    lblRazonSocial.setToolTipText("Ingrese la razón social o el nombre comercial");
    pnlEmpresa.add(lblRazonSocial, "2, 4, left, center");

    JLabel lblEmitirA = new JLabel("Emitir CK A :");
    lblEmitirA.setToolTipText("Ingrese el nombre con que se emitirán CK");
    pnlEmpresa.add(lblEmitirA, "2, 6, left, center");

    JLabel lblRepresentanteLegal = new JLabel("Representante Legal :");
    lblRepresentanteLegal.setToolTipText("Ingrese El nombre del representante Legal.");
    pnlEmpresa.add(lblRepresentanteLegal, "2, 8, left, center");

    JLabel lblContacto = new JLabel("Contacto :");
    lblContacto.setToolTipText("Ingrese nombre del contacto");
    pnlContacto.add(lblContacto, "2, 2, left, center");

    JLabel lblTelefono = new JLabel("Teléfono :");
    lblTelefono.setToolTipText("Ingrese números de teléfonos del contacto");
    pnlContacto.add(lblTelefono, "2, 4, left, center");

    JLabel lblWebSite = new JLabel("Web :");
    lblWebSite.setToolTipText("Ingrese sitio web de la empresa");
    pnlContacto.add(lblWebSite, "2, 6, left, center");

    JLabel lblCorreo = new JLabel("Correo Electronico :");
    lblCorreo.setToolTipText("ingrese correo electrónico de la empresa o contacto");
    pnlContacto.add(lblCorreo, "2, 8, left, center");

    JLabel lblDireccion = new JLabel("Dirección :");
    lblDireccion.setToolTipText("Ingrese dirección de la empresa");
    lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
    pnlContacto.add(lblDireccion, "2, 10, left, center");

    txtRuc = new JTextField();
    txtRuc.setToolTipText(lblRuc.getToolTipText());
    txtRuc.setBackground(bgCampoObligatorio);
    txtRuc.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        return validRuc();
      }
    });
    pnlEmpresa.add(txtRuc, "4, 2, left, top");
    txtRuc.setColumns(20);

    txtRazonSocial = new JTextField();
    txtRazonSocial.setToolTipText(lblRazonSocial.getToolTipText());
    txtRazonSocial.setBackground(bgCampoObligatorio);
    txtRazonSocial.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        return validRazonSocial();
      }
    });
    pnlEmpresa.add(txtRazonSocial, "4, 4, left, top");
    txtRazonSocial.setColumns(30);

    txtEmitirA = new JTextField();
    txtEmitirA.setToolTipText(lblEmitirA.getToolTipText());
    txtEmitirA.setBackground(bgCampoObligatorio);
    txtEmitirA.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        return validEmitirA();
      }
    });
    pnlEmpresa.add(txtEmitirA, "4, 6, left, top");
    txtEmitirA.setColumns(30);

    txtRepresentanteLegal = new JTextField();
    txtRepresentanteLegal.setToolTipText(lblRepresentanteLegal.getToolTipText());
    txtRepresentanteLegal.setBackground(bgCampoObligatorio);
    txtRepresentanteLegal.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        return validRepresentanteLegal();
      }
    });
    pnlEmpresa.add(txtRepresentanteLegal, "4, 8, left, top");
    txtRepresentanteLegal.setColumns(30);

    txtContacto = new JTextField();
    txtContacto.setToolTipText(lblContacto.getToolTipText());
    pnlContacto.add(txtContacto, "4, 2, left, top");
    txtContacto.setColumns(30);

    txtTelefono = new JTextField();
    txtTelefono.setToolTipText(lblTelefono.getToolTipText());
    pnlContacto.add(txtTelefono, "4, 4, left, top");
    txtTelefono.setColumns(30);

    txtWebSite = new JTextField();
    txtWebSite.setToolTipText(lblWebSite.getToolTipText());
    pnlContacto.add(txtWebSite, "4, 6, left, top");
    txtWebSite.setColumns(30);

    txtCorreo = new JTextField();
    txtCorreo.setToolTipText(lblCorreo.getToolTipText());
    pnlContacto.add(txtCorreo, "4, 8, left, top");
    txtCorreo.setColumns(30);

    txtDireccion = new JTextField();
    txtDireccion.setToolTipText(lblDireccion.getToolTipText());
    pnlContacto.add(txtDireccion, "4, 10, left, top");
    txtDireccion.setColumns(30);

    for (var comp : pnlEmpresa.getComponents()) {
      if (comp instanceof JTextField) {
        ((JTextField) comp).addKeyListener(new java.awt.event.KeyAdapter() {
          // Habilita el boton guardar cuando digitamos algun caracter en el textfield
          @Override
          public void keyPressed(java.awt.event.KeyEvent evt) {
            if (!btnGuardar.isEnabled()) {
              btnGuardar.setEnabled(true);
            }
          }
        });
      }
    }
    JButton btnCerrar = new JButton("Cerrrar");
    btnCerrar.setFocusable(false);
    btnCerrar.setToolTipText("Cierra el Formulario de empresa");
    btnCerrar.setPreferredSize(new Dimension(120, 40));
    btnCerrar.addActionListener((ActionEvent arg0) -> {
      cerrar();
    });
    btnGuardar.setFocusable(false);
    btnGuardar.setToolTipText("Guardar los datos de la empresa");
    btnGuardar.setPreferredSize(new Dimension(120, 40));
    btnGuardar.setEnabled(false);
    btnGuardar.addActionListener((ActionEvent arg0) -> {
      guardar();
    });

    panel_button.add(btnCerrar);
    panel_button.add(btnGuardar);
    pack();
  }

  private boolean validRuc() {
    if (txtRuc.getText().isEmpty()) {
      showMessaje("Numero RUC, no puede estar vacío");
      return false;
    }
    return true;
  }

  private boolean validRazonSocial() {
    if (txtRazonSocial.getText().isEmpty()) {
      showMessaje("Razón Social, no puede estar vacío");
      return false;
    }
    return true;
  }

  private boolean validEmitirA() {
    if (txtEmitirA.getText().isEmpty()) {
      showMessaje("Emitir CK a,  no puede estar vacío");
      return false;
    }
    return true;
  }

  private boolean validRepresentanteLegal() {
    if (txtRepresentanteLegal.getText().isEmpty()) {
      showMessaje("Nombre del representante legal, no puede estar vacío");
      return false;
    }
    return true;
  }

  private void showMessaje(String mensaje) {
    StringBuilder strMensaje = new StringBuilder();
    byte ii = 0;
    for (short i = 0; mensaje.length() > i && 500 > i; i++) {
      strMensaje.append(mensaje.charAt(i));
      ii++;
      if (ii >= 75) {
        strMensaje.append("\n");
        ii = 0;
      }
    }
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), strMensaje.toString(), getTitle(),
        JOptionPane.ERROR_MESSAGE);
  }

  /**
   * obtiene imagen almacenada
   *
   */
  private void obtenerImagen() {
    JFileChooser frdGetFile = new JFileChooser();
    frdGetFile.setCurrentDirectory(new File(System.getProperty("user.home")));
    frdGetFile.setFileFilter(new FileNameExtensionFilter("Archivo de Imagenes", "png"));
    int opcion = frdGetFile.showOpenDialog(this);
    if (opcion == JFileChooser.APPROVE_OPTION) {
      ImageIcon imgiconTemp = new ImageIcon(frdGetFile.getSelectedFile().toString());
      String imgDescrip = imgiconTemp.getDescription();
      imgLogo = imgiconTemp;
      imgLogo.setImage(imgiconTemp.getImage().getScaledInstance(lblImagen.getWidth(),
          lblImagen.getHeight(), Image.SCALE_DEFAULT));
      imgLogo.setDescription(imgDescrip);
      lblImagen.setIcon(imgLogo);
    }
  }

  /**
   * Rellena los campos del formaulario, obteniendo los datos de la base de datos
   */
  private void rellenarCamposForm() {
    java.util.List<Empresa> list = this.empresaDao.getAll();
    // TODO: Ver la posibilidad de administrar varias empresas.
    datosEmpresa = (list != null && !list.isEmpty()) ? list.get(0) : null;

    if (datosEmpresa != null) {
      txtRuc.setText(datosEmpresa.getRuc());
      txtRazonSocial.setText(datosEmpresa.getRazonSocial());
      txtRepresentanteLegal.setText(datosEmpresa.getRepresentanteLegal());
      txtEmitirA.setText(datosEmpresa.getEmitirA());
      txtContacto.setText(datosEmpresa.getContacto());
      txtCorreo.setText(datosEmpresa.getCorreo());
      txtTelefono.setText(datosEmpresa.getTelefono());
      txtWebSite.setText(datosEmpresa.getWebSite());
      txtDireccion.setText(datosEmpresa.getdireccion());
    }
  }

  // Almacena los datos del formulario en la entidad MiEmpresa
  private boolean loadEmpresa() {
    if (!validRuc()) {
      txtRuc.requestFocus();
      return false;
    }
    if (!validRazonSocial()) {
      txtRazonSocial.requestFocus();
      return false;
    }
    if (!validRepresentanteLegal()) {
      txtRepresentanteLegal.requestFocus();
      return false;
    }
    if (!validEmitirA()) {
      txtEmitirA.requestFocus();
      return false;
    }
    datosEmpresa = new Empresa();
    datosEmpresa.setRuc(txtRuc.getText());
    datosEmpresa.setRazonSocial(txtRazonSocial.getText());
    datosEmpresa.setRepresentanteLegal(txtRepresentanteLegal.getText());
    datosEmpresa.setEmitirA(txtEmitirA.getText());
    datosEmpresa.setContacto(txtContacto.getText());
    datosEmpresa.setCorreo(txtCorreo.getText());
    datosEmpresa.setTelefono(txtTelefono.getText());
    datosEmpresa.setWebSite(txtWebSite.getText());
    datosEmpresa.setdireccion(txtDireccion.getText());
    return true;
  }

  // Guarda las modificaciones del formulario
  private void guardar() {
    if (loadEmpresa()) {
      try {
        Utilities.cp(imgLogo, IMAGENPATH);
      } catch (IOException ex) {
        Logger.getLogger(EmpresaView.class.getName()).log(Level.SEVERE, null, ex);
      }

      empresaDao.save(datosEmpresa);
      cerrar();
    }

  }

  protected void cerrar() {
    this.dispose();
  }

  @Override
  protected void initView() {
    // establciendo el valor del Sigleton
    initEmpresa();
    miInstancia = this;
    rellenarCamposForm();
  }

  @Override
  protected void close() {
    super.close();
    empresaDao.close();
  }

}
