package org.jugni.apps.pico.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.security.UserSession;
import org.jugni.apps.pico.security.exception.InvalidAccessException;

@SuppressWarnings("serial")
public class LoginView extends JInternalFrame {

  private static LoginView instance;

  public static LoginView getInstance(PicoApplication application) throws InvalidAccessException {
    if (instance == null) {
      synchronized (LoginView.class) {
        instance = new LoginView(application);
      }
    }

    return instance;
  }

  private final PicoApplication application;
  private final UserSession userSession;

  private LoginView(PicoApplication application) {
    this.application = application;
    this.userSession = application.getRootView().getUserSession();
    this.init();
  }

  private void init() {
    setToolTipText("Login");
    setOpaque(false);
    setIconifiable(false);
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setResizable(false);
    setTitle("Login");
    setClosable(true);
    setName("Login");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    getContentPane().setPreferredSize(new Dimension(500, 280));

    var container = new JPanel();
    container.setLayout(new GridBagLayout());
    var c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 3;
    c.anchor = GridBagConstraints.PAGE_START;
    c.insets = new Insets(15, 40, 30, 40);
    var mTitle = new JLabel("Bienvenido a PICO");
    mTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
    container.add(mTitle, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    c.insets = new Insets(20, 0, 0, 0);
    var mLabelUsername = new JLabel("Usuario: ");
    mLabelUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
    container.add(mLabelUsername, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 1;
    c.insets = new Insets(5, 0, 0, 0);
    var mLabelPassword = new JLabel("Clave: ");
    mLabelPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
    container.add(mLabelPassword, c);

    c.gridx = 1;
    c.gridy = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.insets = new Insets(5, 0, 0, 0);
    c.gridwidth = 2;
    var mUsername = new JTextField();
    mUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
    container.add(mUsername, c);

    c.gridx = 1;
    c.gridy = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.insets = new Insets(5, 0, 0, 0);
    c.gridwidth = 2;
    var mPassword = new JPasswordField();
    mPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
    container.add(mPassword, c);


    c.gridx = 0;
    c.gridy = 4;
    c.anchor = GridBagConstraints.PAGE_END;
    c.insets = new Insets(18, 40, 5, 40);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 3;
    var mLogin = new JButton("Login");
    mLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
    container.add(mLogin, c);

    mLogin.addActionListener(v -> {
      try {
        this.userSession.login(mUsername.getText(), String.valueOf(mPassword.getPassword()));
        mPassword.setText("");
        mUsername.setText("");

        application.getRootView().setCurrentStatus("Inicio de sesión exitoso. Bienvenido "
            + userSession.getUserInfo().getUsername() + ".");
        this.dispose();
      } catch (InvalidAccessException e) {
        application.getRootView().setCurrentStatus("Error: " + e.getMessage());
      }
    });


    getContentPane().add(container, BorderLayout.CENTER);
    pack();
  }



}
