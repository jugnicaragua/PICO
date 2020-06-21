package org.ni.jug.pico.vista;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.ni.jug.pico.PicoCoreApplication;
import org.ni.jug.pico.core.boundary.CuentaTipoService;
import org.ni.jug.pico.core.model.CuentaTipo;
import org.ni.jug.pico.vista.util.ButtonContructor;
import org.ni.jug.pico.vista.util.CerrarButton;
import org.ni.jug.pico.vista.util.GuardarButton;

/**
 * @author Gustavo Castro <gacsnic75@gmail.com>
 * @version 0.0.1
 */
public class CuentaTipoForm extends JInternalFrame {

    private static final CuentaTipoForm INSTANCE = new CuentaTipoForm();

    private JComboBox cmbCuentaTipo;
    private List<CuentaTipo> cuentaTipos;
    private CuentaTipoCBModel cuentaTipoModel;

    private CuentaTipoForm() {
        initCuentaTipoForm();
    }

    private void initCuentaTipoForm() {
        //Caracteristicas del formulario
        setToolTipText("Formulario de tipo de cuenta");
        setOpaque(true);
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setIconifiable(false);
        setTitle("Tipo de cuenta");
        setClosable(true);
        setName("Tipo_De_Cuenta");

        //se crear una etiqueta para titulo del formulario
        JLabel lblTitle = new JLabel("Tipos de cuenta - Crear/Editar ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(40, 40));

        //Instanciar objetos contenidos en el formulario
        cuentaTipoModel = new CuentaTipoCBModel();
        JLabel lblCuentaTipo = new JLabel("Tipo de cuenta:");
        JPanel pnlTitle = new JPanel(); //Panel que contiene el titulo del formulario
        JPanel pnlCentral = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel pnlButton = new JPanel();
        CerrarButton btnCerrar = new CerrarButton();
        GuardarButton btnGuardar = new GuardarButton();
        var btnAyuda = ButtonContructor.createButtonDialogo("Ayuda");
        cmbCuentaTipo = new JComboBox();

        //se define caracteristicas de objetos del formulario
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));
        pnlTitle.setLayout(new BoxLayout(pnlTitle, BoxLayout.LINE_AXIS));
        pnlTitle.setBackground(new Color(0, 117, 175));
        lblCuentaTipo.setToolTipText("Descripción de tipo de cuenta");
        cmbCuentaTipo.setEditable(true);
        cmbCuentaTipo.setPreferredSize(new Dimension(300, 30));
        cmbCuentaTipo.setModel(cuentaTipoModel);
        //Al digitar caracteres en el editor Habilita el btnGuardar
        cmbCuentaTipo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (!btnGuardar.isEnabled()) {
                    btnGuardar.setEnabled(true);
                }
            }
        });
        panel.add(lblCuentaTipo);
        panel.add(cmbCuentaTipo);

        btnCerrar.addActionListener((ActionEvent arg0) -> {
            dispose();
        });
        btnGuardar.addActionListener((ActionEvent arg0) -> {
            guardar();
            btnGuardar.setEnabled(false);
        });

        //Agrega los componentes al formulario
        pnlTitle.add(Box.createRigidArea(new Dimension(10, 10)));
        pnlTitle.add(lblTitle);
        pnlButton.add(btnAyuda);
        pnlButton.add(Box.createRigidArea(new Dimension(60, 10)));
        pnlButton.add(btnCerrar);
        pnlButton.add(btnGuardar);
        pnlCentral.add(panel);
        getContentPane().add(pnlTitle, BorderLayout.NORTH);
        getContentPane().add(pnlCentral, BorderLayout.CENTER);
        getContentPane().add(pnlButton, BorderLayout.SOUTH);
        pack();
    }

    private void guardar() {
        CuentaTipo ct = new CuentaTipo();
        if (null == cmbCuentaTipo.getSelectedItem()) {
            ct.setDescripcion(cmbCuentaTipo.getEditor().getItem().toString());
        } else {
            ct.setDescripcion(cmbCuentaTipo.getEditor().getItem().toString());
            ct.setId(cuentaTipos.get(cmbCuentaTipo.getSelectedIndex()).getId());
        }
        PicoCoreApplication.getBean(CuentaTipoService.class).actualizar(ct);
        cuentaTipoModel.update();
        cmbCuentaTipo.repaint();
        cmbCuentaTipo.getEditor().setItem(null);
        cmbCuentaTipo.setSelectedItem(null);

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
            cuentaTipos = PicoCoreApplication.getBean(CuentaTipoService.class).buscarTodos();
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

    public static CuentaTipoForm getInstancia() {
        return INSTANCE;
    }
}
