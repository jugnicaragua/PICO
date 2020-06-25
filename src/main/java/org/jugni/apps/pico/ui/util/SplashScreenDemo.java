package org.jugni.apps.pico.ui.util;


/***************************************************************************
 * $name: SplashScreen.java $version: 1.0 $date_modified: 210104 $description: $owner: Beans for
 * Novell Services Team Copyright (c) 1998 Novell, Inc. All Rights Reserved.
 * 
 * THIS WORK IS SUBJECT TO U.S. AND INTERNATIONAL COPYRIGHT LAWS AND TREATIES. USE AND
 * REDISTRIBUTION OF THIS WORK IS SUBJECT TO THE LICENSE AGREEMENT ACCOMPANYING THE SOFTWARE
 * DEVELOPMENT KIT (SDK) THAT CONTAINS THIS WORK. PURSUANT TO THE SDK LICENSE AGREEMENT, NOVELL
 * HEREBY GRANTS TO DEVELOPER A ROYALTY-FREE, NON-EXCLUSIVE LICENSE TO INCLUDE NOVELL'S SAMPLE CODE
 * IN ITS PRODUCT. NOVELL GRANTS DEVELOPER WORLDWIDE DISTRIBUTION RIGHTS TO MARKET, DISTRIBUTE, OR
 * SELL NOVELL'S SAMPLE CODE AS A COMPONENT OF DEVELOPER'S PRODUCTS. NOVELL SHALL HAVE NO
 * OBLIGATIONS TO DEVELOPER OR DEVELOPER'S CUSTOMERS WITH RESPECT TO THIS CODE.
 ****************************************************************************/

// package com.novell.application.console.shell;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Simple splash screen. It is created in a static initializer inside the console class and stays up
 * for the given amount of seconds passed in on construction.
 */
class SplashScreenDemo extends Window {
  /**
   * 
   */
  private static final long serialVersionUID = 13233424L;
  private static final int m_iconsHeight = 30;
  private static final int m_iconsWidth = 30;

  private int m_splashWidth;
  private int m_splashHeight;
  private ImageIcon m_icon; // splash image
  private ImageIcon[] m_icons = new ImageIcon[100]; // product icons
  private JLabel m_splashLabel;
  private JPanel m_iconsPanel;
  private boolean m_bKilled = false;
  private int m_iconsIdx = 0; // number of product icons
  private boolean m_bLayout = false;
  private boolean m_bWaiting = false;



  SplashScreenDemo(Frame frame, Image image) {
    super((null == frame) ? new Frame() : frame);
    try {
      setLayout(new BorderLayout());
      changeSplashImage(new ImageIcon(image));
    } catch (Exception e) {
      System.out.println("Exception in SplashScreen constructor - " + e);
      // don't try to do anything else if there was a problem.
      return;
    } catch (Error e) {
      System.out.println("Error in SplashScreen constructor - " + e);
      // don't try to do anything else if there was a problem.
      return;
    }

    // interrupt the thread if the mouse is clicked.
    MouseAdapter ma = new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        kill();
      }
    };

    // addMouseListener( ma );

    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
          kill();
        }
      }
    });

    // change the cursor to the wait cursor
    startWaitCursor();
  }



  /**
   * Only constructor for splash screen.
   *
   * @param frame the frame for this window, if available
   * @param image the image to display
   */
  SplashScreenDemo(Frame frame, ImageIcon image) {
    super((null == frame) ? new Frame() : frame);
    try {
      setLayout(new BorderLayout());
      changeSplashImage(image);
    } catch (Exception e) {
      System.out.println("Exception in SplashScreen constructor - " + e);
      // don't try to do anything else if there was a problem.
      return;
    } catch (Error e) {
      System.out.println("Error in SplashScreen constructor - " + e);
      // don't try to do anything else if there was a problem.
      return;
    }

    // interrupt the thread if the mouse is clicked.
    MouseAdapter ma = new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        kill();
      }
    };

    // addMouseListener( ma );

    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
          kill();
        }
      }
    });

    // change the cursor to the wait cursor
    startWaitCursor();
  }

  public void setVisible(final boolean visible) {
    Runnable runner = new Runnable() {
      public void run() {
        superSetVisible(visible);
      }
    };

    Utilities.runOnEventThreadLater(runner);
  }

  private void superSetVisible(boolean visible) {
    super.setVisible(visible);
  }

  // Sets the dialog cursor to a wait cursor.
  private void startWaitCursor() {
    if (!m_bWaiting) {
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      m_bWaiting = true;
    }
  }

  // Sets the cursor back to normal from a wait cursor.
  private void endWaitCursor() {
    if (m_bWaiting) {
      m_bWaiting = false;
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  /**
   * Set the splash screen background, icons panel and redisplay window.
   */
  synchronized public void changeSplashImage(final ImageIcon icon) {
    Runnable runner = new Runnable() {
      public void run() {
        m_icon = icon;
        m_splashHeight = m_icon.getIconHeight();
        m_splashWidth = m_icon.getIconWidth();

        // set the window size based on the size of splash image
        setSize(m_splashWidth, m_splashHeight + m_iconsHeight + 12);
        // D.out("Splash screen w,h:
        // "+m_icon.getIconWidth()+","+m_icon.getIconHeight());

        // define splashlabel layout
        m_splashLabel = new JLabel(m_icon) {
          public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int tleft = 76;
            int ttop = 133;
            Font f = g.getFont();
            Font newFont = new Font(f.getName(), f.getStyle(), f.getSize() - 2);
            g.setFont(newFont);
            // g.drawString(Version.getDisplayVersion(), this.getInsets().left+tleft,
            // this.getInsets().top+ttop);
          }
        };

        displaySplash();
      }
    };

    Utilities.runOnEventThreadLater(runner);
  }

  /**
   * Add text to the bottom of the splash screen.
   */
  synchronized public void addText(final String msg) {
    Runnable runner = new Runnable() {
      public void run() {
        m_iconsPanel.removeAll();

        // ******************Cygsoft modification starts*********************
        // Company : Cygsoft Inc.
        // Author : <Vinod Venugopal>
        // Peer Reviewer : <Bhavesh Shah>
        // Code change date : <20 May,2001>
        // Comments :adding accessibility
        // ******************************************************************

        // clear and setup the icon panel
        JLabel txtLabel = new JLabel(msg);// COMMENTED BY VINOD V
        // JLabel txtLabel = NConeFactory.novellJLabel(new JLabel(msg),msg,"");//ADDED BY VINOD V

        // ******************Cygsoft modification ends*********************

        Dimension d = txtLabel.getPreferredSize();
        d.height = m_iconsHeight;
        txtLabel.setPreferredSize(d);
        m_iconsPanel.add(txtLabel);

        // redisplay icon panel on splash window
        m_iconsPanel.doLayout();
        m_iconsPanel.repaint();
      }
    };

    // Utilities.runOnEventThreadLater(runner);
  }

  /**
   * Add an icon to the bottom of the splash screen.
   */
  synchronized public void addIcon(final ImageIcon icon) {
    Runnable runner = new Runnable() {
      public void run() {
        int numIcons = m_splashWidth / m_iconsWidth;

        if (m_iconsIdx < numIcons) {
          // D.out("Adding icon " + icon);
          m_icons[m_iconsIdx] = icon;
          m_iconsIdx++;
        } else {
          // move everything down one and then insert it
          for (int i = 0; i < numIcons - 1; i++) {
            m_icons[i] = m_icons[i + 1];
          }
          m_icons[numIcons - 1] = icon;
          m_bLayout = true;
        }

      }
    };

    Utilities.runOnEventThreadLater(runner);
  }

  /**
   * Setup the window layout and display it.
   */
  synchronized private void displaySplash() {
    removeAll(); // remove all containers from window

    // add mainLabel to window
    add(m_splashLabel, BorderLayout.CENTER);
    pack();

    // Center the dialog over the window
    Utilities.centerWindow(this);
  }

  /**
   * Kill the splash screen.
   */
  synchronized public void kill() {
    Runnable runner = new Runnable() {
      public void run() {
        if (!m_bKilled) { // If the splashScreen has not been killed before
          dispose();
          m_bKilled = true;

          // set the cursor back to the default cursor
          endWaitCursor();
        }
      }
    };

    Utilities.runOnEventThreadLater(runner);
  }

};
