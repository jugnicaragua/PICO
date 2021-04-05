package org.jugnicaragua.app.pico.vista.catalogo;

import org.jugnicaragua.app.pico.vista.menu.MenuPrincipalAcciones;
import org.jugnicaragua.app.pico.dao.CuentaTipoImpl;
import org.jugnicaragua.app.pico.modelo.CuentaTipo;
import org.jugnicaragua.app.pico.vista.utils.ButtonContructor;
import org.jugnicaragua.app.pico.vista.utils.CerrarButton;
import org.jugnicaragua.app.pico.vista.utils.GuardarButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.swing.Box.*;

/**
* <strong> org.jugni.apps.pico.vista.swing </strong>
*
* @author :Gustavo Castro <gacsnic75@gmail.com>
* @version : 0.1.0
* @license : GPLv3
*

* Clase CuentaForm : Formulario para los catalogo de cuentas
*/
public class CuentaForm extends JInternalFrame {
    private static CuentaForm INSTANCE;
    private GridBagConstraints constraints;
    private GridBagLayout grid;
    private JPanel pnlCentral;
    private List<CuentaTipo> cuentaTipos;
    private CuentaTipoCBModel cuentaTipoModel;
    private JComboBox cmbCuentaTipo;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox cmbNaturaleza;

    private CuentaForm(){
        INSTANCE = this;
        initCuentaForm();
    }

    public static CuentaForm getInstancia(){
        return (INSTANCE == null) ? new CuentaForm() : INSTANCE;
    }

    private void initCuentaForm(){
        //Instaciar objetos del formulario aprovenchando que estamos con java 11 utilizaremos var
        constraints=new GridBagConstraints();
        grid =new GridBagLayout();
        pnlCentral = new JPanel(grid);
        cuentaTipoModel = new CuentaTipoCBModel();
        cmbCuentaTipo = new JComboBox();
        var bgCampoObligatorio = new Color(255, 228, 181); //Color de fondo para los campos que son obligatorios
        var lblTitle = new JLabel("Catalogo de cuenta");
        var pnlTitle = new JPanel(); //Panel que contiene el titulo del formulario
        var lblTipoCuenta= new JLabel("Tipo :");
        var lblCodigo= new JLabel("Codigo :");
        var lblNaturaleza= new JLabel("Naturaleza :");
        var lblNombre =new JLabel("Nombre :");
         txtCodigo =new JTextField();
        txtNombre =new JTextField();
        cmbNaturaleza =new JComboBox();
        var txtDescrip= new JTextPane();
        var pnlButton = new JPanel();
        var btnCerrar = new CerrarButton();
        var btnGuardar = new GuardarButton();
        var btnCuentaTipo = new JButton("...");
        var btnAyuda= ButtonContructor.createButtonDialogo("Ayuda");

       // ******** Casracterisiticas de los objetos que contiene el formulario**********
        //se crear una etiqueta para titulo del formulario
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(40, 40));
        pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.LINE_AXIS));
        pnlTitle.setBackground(new Color(0, 117, 175));
        pnlTitle.add(createRigidArea(new Dimension(10, 10)));
        pnlTitle.add(lblTitle);


        btnCerrar.addActionListener((ActionEvent arg0) -> {
            cerrar();
        });
        btnCuentaTipo.addActionListener((ActionEvent arg0) -> {
            MenuPrincipalAcciones.mostrarVentanaCuentaTipo();
        });

        btnCuentaTipo.setToolTipText("Llama al formulario de tipo de cuenta");
        btnCuentaTipo.setFocusable(false);

        lblCodigo.setToolTipText("Ingrese codigo de la cuenta");
        txtCodigo.setBackground(bgCampoObligatorio);
        txtCodigo.setToolTipText(lblCodigo.getToolTipText());
        lblNombre.setToolTipText("Ingrese nombre de la cuenta");
        txtNombre.setBackground(bgCampoObligatorio);
        txtNombre.setToolTipText(lblNombre.getToolTipText());
        lblTipoCuenta.setToolTipText("Selecione un tipo de cuenta");
        cmbCuentaTipo.setBackground(bgCampoObligatorio);
        cmbCuentaTipo.setToolTipText(lblTipoCuenta.getToolTipText());
        lblNaturaleza.setToolTipText("Selecione la naturaleza de la cuenta");cmbNaturaleza.setBackground(bgCampoObligatorio);
        cmbNaturaleza.setToolTipText(lblNaturaleza.getToolTipText());

        cmbCuentaTipo.setModel(cuentaTipoModel);
        txtDescrip.setText("TODO: definir que se describira en este campo, lorem ips");
        //Agregar componente al gridLayout
        pnlCentral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "General", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));
        constraints.fill=GridBagConstraints.BOTH;
        agregarcomponenteAlGrid(lblTipoCuenta,0,0,1,1);
        agregarcomponenteAlGrid(lblCodigo,1,0,1,1);
        agregarcomponenteAlGrid(lblNombre,2,0,1,1);
        constraints.weightx = 1000; // puede hacerse más ancho
        agregarcomponenteAlGrid(cmbCuentaTipo,0,1,1,1);
        agregarcomponenteAlGrid(txtCodigo,1,1,1,1);
        agregarcomponenteAlGrid(txtNombre,2,1,3,1);
        constraints.weightx = 0; // nopuede hacerse más ancho
        agregarcomponenteAlGrid(btnCuentaTipo,0,2,1,1);
        agregarcomponenteAlGrid(lblNaturaleza,0,3,1,1);
        constraints.weightx = 1000; // puede hacerse más ancho
        agregarcomponenteAlGrid(cmbNaturaleza,0,4,1,1);
        constraints.weightx = 1000; // puede hacerse más ancho
        agregarcomponenteAlGrid(txtDescrip,3,0,5,1);

        pnlButton.add(btnAyuda);
        pnlButton.add(Box.createRigidArea(new Dimension(60, 10)));
        pnlButton.add(btnCerrar);
        pnlButton.add(btnGuardar);
        getContentPane().add(pnlTitle, BorderLayout.NORTH);
        getContentPane().add(pnlCentral, BorderLayout.CENTER);
        getContentPane().add(pnlButton, BorderLayout.SOUTH);

        //Caracteristicas del formulario
        setToolTipText("Formulario Catalogo de cuentas");
        setOpaque(true);
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setIconifiable(false);
        setTitle("Catalogo de cuentas");
        setClosable(true);
        setName("Cuenta");
        getContentPane().setPreferredSize(new Dimension(600,280));

        //Se establece las teclas que al presionar avance el foco
        Set<AWTKeyStroke> keyFoco = new HashSet<>();
        keyFoco.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_ENTER, 0));
        keyFoco.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_TAB, 0));
        getContentPane().setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                keyFoco);
        pack();
    }
        private void agregarcomponenteAlGrid( Component componente,
        int fila, int columna, int anchura, int altura )
        {
            constraints.gridx = columna; // establece gridx
            constraints.gridy = fila; // establece gridy
            constraints.gridwidth = anchura; // establece gridwidth
            constraints.gridheight = altura; // establece gridheight
            grid.setConstraints( componente, constraints ); // establece restricciones
            pnlCentral.add( componente ); // agrega el componente
        } 

    protected void cerrar() {
        this.dispose();
        INSTANCE = null;
    }

    /**
     * ListModal para el combobox, lista los tipos de cuenta que se encuentran en la base de datos
     */
    class CuentaTipoCBModel extends AbstractListModel implements ComboBoxModel {

        String id;

        public CuentaTipoCBModel() {
            update();
        }

        public void update() {
            cuentaTipos = new CuentaTipoImpl().obtenerRegistros();
        }

        @Override
        public int getSize() {
            return cuentaTipos.size();
        }

        @Override
        public Object getElementAt(int i) {
            return cuentaTipos.get(i).getDescripcion();
        }

        @Override
        public void setSelectedItem(Object o) {
            id = (String) o;
        }

        @Override
        public Object getSelectedItem() {
            return id;
        }

    }

}
