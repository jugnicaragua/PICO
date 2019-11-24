package org.jugni.apps.pico.vista.swing;

// Sample code file: Utilities.java
// Warning: This code has been marked up for HTML

/***************************************************************************
$name: Utilities.java 
$version: 1.0 
$date_modified: 210104
$description:  
$owner: Beans for Novell Services Team 
Copyright (c) 1998 Novell, Inc. All Rights Reserved.

THIS WORK IS SUBJECT TO U.S. AND INTERNATIONAL COPYRIGHT LAWS AND TREATIES.
USE AND REDISTRIBUTION OF THIS WORK IS SUBJECT TO THE LICENSE AGREEMENT
ACCOMPANYING THE SOFTWARE DEVELOPMENT KIT (SDK) THAT CONTAINS THIS WORK.
PURSUANT TO THE SDK LICENSE AGREEMENT, NOVELL HEREBY GRANTS TO DEVELOPER A
ROYALTY-FREE, NON-EXCLUSIVE LICENSE TO INCLUDE NOVELL'S SAMPLE CODE IN ITS
PRODUCT. NOVELL GRANTS DEVELOPER WORLDWIDE DISTRIBUTION RIGHTS TO MARKET,
DISTRIBUTE, OR SELL NOVELL'S SAMPLE CODE AS A COMPONENT OF DEVELOPER'S
PRODUCTS. NOVELL SHALL HAVE NO OBLIGATIONS TO DEVELOPER OR DEVELOPER'S
CUSTOMERS WITH RESPECT TO THIS CODE.
****************************************************************************/

//package com.novell.application.console.shell;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

//import com.objectspace.jgl.*;
//import com.novell.application.console.snapin.scope.*;
//import com.novell.application.console.snapin.context.*;
//import com.novell.application.console.snapin.*;
//import com.novell.utility.nmsgbox.NMsgBox;

/**
 * Class with helper utilities that have no other home.
 */
//REMIND: Add asserts.
public class Utilities {

	public static void dumpProperties() {
		Properties sysProps = System.getProperties();

		Enumeration keys = sysProps.keys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
		}
	}

	/*
	 * public static boolean isEnumVirtual(ObjectEntryEnumeration enum) { boolean
	 * virtual = (enum instanceof BrowserObjectEntryEnumeration);
	 * 
	 * if(virtual && (enum instanceof DefaultIndexedObjectEntryEnumeration)) {
	 * virtual = false; }
	 * 
	 * return virtual; }
	 */
	/*
	 * public static boolean checkForPageSnapins(ObjectEntryCollection entries) { //
	 * Only show the Properties menu item if their are pages registered.
	 * 
	 * //REMIND: OK to pass null for MPEC parameter? InitSnapinInfo aInfo = new
	 * InitSnapinInfo( MainShell.getInstance(), Shell.SNAPIN_PAGE, new
	 * PageSnapinContext(null, entries)); aInfo.setTracking( false ); boolean
	 * pagesExist = Registry.checkSnapins(Scopes.page(entries.getFirstElement()),
	 * aInfo );
	 * 
	 * //Array pageSnapins =
	 * Registry.getSnapins(Scopes.page(entries.getFirstElement()), aInfo );
	 * //if(pageSnapins != null && pageSnapins.size() > 0)
	 * 
	 * if(pagesExist) { return true; } else { return false; } }
	 * 
	 * public static ObjectEntry getMappedContainer(ObjectEntry oe) throws
	 * SnapinException { //REMIND: Bug, we only return the first oe, what to do
	 * though? ObjectEntry[] oea = ShellStubs.mapObjectEntry(oe);
	 * 
	 * ObjectEntry ret = oe;
	 * 
	 * if(oea != null && oea.length > 0) { ret = oea[0]; }
	 * 
	 * return ret; }
	 */
	public static void runOnEventThreadAndWait(Runnable runner) {
		if (SwingUtilities.isEventDispatchThread()) {
			runner.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(runner);
			} catch (Exception e) {
				// REMIND: What to really do here?
				// D.reportException("Exception trying to invoke process on event thread.", e);
			}
		}
	}

	public static void runOnEventThreadLater(Runnable runner) {
		if (SwingUtilities.isEventDispatchThread()) {
			runner.run();
		} else {
			SwingUtilities.invokeLater(runner);
		}
	}

	/**
	 * Centers window based on screen size.
	 */
	public static void centerWindow(Window windowToCenter) {
		Dimension size = windowToCenter.getSize();
		int width = size.width;
		int height = size.height;
		int x = 0;
		int y = 0;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		x = (screen.width - width) / 2;
		y = (screen.height - height) / 2;
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		windowToCenter.setLocation(x, y);
	}

	/**
	 * Centers component based on a parent component size.
	 */
	public static void centerComponentOnComponent(Component parent, Component child) {
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Is the parent invisible or have no size?
		if (!parent.isVisible() || (parent.getSize().width == 0) || (parent.getSize().height == 0)) {
			// Yes. Treat the entire desktop as the parent.
			x = 0;
			y = 0;
			width = screenSize.width;
			height = screenSize.height;
		} else {
			// No. Center over the parent.
			x = parent.getLocationOnScreen().x;
			y = parent.getLocationOnScreen().y;
			width = parent.getSize().width;
			height = parent.getSize().height;
		}

		// Calculate a centered x,y location for the child component
		x = Math.max(0, x + (width - child.getSize().width) / 2);
		y = Math.max(0, y + (height - child.getSize().height) / 2);

		// Make sure that the whole component can fit on the screen,
		// if not push it back a little
		x = Math.min(x, screenSize.width - child.getSize().width);
		y = Math.min(y, screenSize.height - child.getSize().height);

		child.setLocation(x, y);
	}

	public static Hashtable combineHashtables(Hashtable h1, Hashtable h2) {
		Hashtable both = (Hashtable) h1.clone();

		Enumeration enume = h2.elements();
		Enumeration keys = h2.keys();

		while (keys.hasMoreElements()) {
			both.put((String) keys.nextElement(), (String) enume.nextElement());
		}

		return both;
	}

	public static void showPopup(JPopupMenu p, Component c, int x, int y) {

		boolean submenu = false;
		if (null != p && null != c) {
			// D.out("mouse clicked at "+x+","+y);
			Component mi;
			for (int i = 0; i < p.getComponentCount(); i++) {
				mi = p.getComponentAtIndex(i);
				if (mi instanceof JMenu) {
					submenu = true;
					break;
				}
			}

			Point pt = c.getLocationOnScreen();
			// D.out("ConsoleOne at screen loc "+pt.x+","+pt.y);
			// int mx = pt.x+x;
			// int my = pt.y+y;
			// D.out("Mouse clicked at screen loc "+mx+","+my);
			Dimension sizePopup = p.getPreferredSize();
			Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
			if (null != sizePopup && null != sizeScreen) {
				// off right or bottom and has submenus?
				/*
				 * if ( submenu && ( (pt.x+x+sizePopup.width > sizeScreen.width) || (pt.y + y +
				 * sizePopup.height > sizeScreen.height) ) ) { // center popup menu w/ submenus
				 * at center of screen //x = (sizeScreen.width/2)-(pt.x)-sizePopup.width/2; //y
				 * = (sizeScreen.height/2)-(pt.y)-sizePopup.height/2;
				 * 
				 * // keep it from extending off bottom of screen if (pt.y + y +
				 * sizePopup.height > sizeScreen.height) { // move it up just enough to fit y -=
				 * (pt.y + y + sizePopup.height) - sizeScreen.height + 5; } }
				 * 
				 * // off right side of screen? else
				 */ if (pt.x + x + sizePopup.width > sizeScreen.width) {
					// move it to left of cursor
					x -= sizePopup.width;
					y -= sizePopup.height;
				}

				// off bottom of screen?
				else if (pt.y + y + sizePopup.height > sizeScreen.height) {
					// move it above cursor
					y -= sizePopup.height;
				}

				// no matter what, make sure it doesn't go off top of screen
				if (pt.y + y < 0) {
					y = -pt.y + 5;
				}

				// D.out("popup size: pu.w="+sizePopup.width+" pu.h="+sizePopup.height);
				// D.out("screen size: sc.w="+sizeScreen.width+" sc.h="+sizeScreen.height);
				// D.out("show popup loc = "+x+","+y);
			}

			p.show(c, x, y);
		}
	}

	public static void sortStrings(String[] a) {
		int size, diff, n, m, max, swapPt;
		String tmp;

		if (a != null && ((size = a.length) > 1)) {
			// D.out("Array size = "+size);
			diff = size;
			while (diff > 1) {
				diff = diff / 2;
				max = size - 1;
				while (max > diff) {
					swapPt = 0;
					for (m = 0; m <= max - diff; m++) {
						n = m + diff;
						// D.out(">>>Comparing m="+m+" to n="+n);
						if (a[m].compareTo(a[n]) > 0) {
							// swap em
							// D.out("<>Swapping m="+m+" n="+n);
							tmp = a[m];
							a[m] = a[n];
							a[n] = tmp;
							swapPt = m;
						}
					} // for
					max = swapPt;
				} // max>diff

			} // diff>1
		}
	}// sortStrings

	/**
	 * getFrame()
	 */
	public static Frame getParentFrame(Component parent) {
		Frame frame = null;

		if (parent == null) {
			frame = new Frame();
		} else if (!(parent instanceof Frame)) {
			Component grandComp = null;
			grandComp = parent.getParent();

			// Walk the parent chain to see if there is a frame somewhere
			while (grandComp != null) {
				if (grandComp instanceof Frame) {
					frame = (Frame) grandComp;
					break;
				}

				grandComp = grandComp.getParent();
			}

			if (frame == null) {
				frame = new Frame();
			}
		} else {
			frame = (Frame) parent;
		}

		return frame;
	}
	

public static void cp(String oringen, String destino) throws IOException {
    BufferedInputStream in = null;
    BufferedOutputStream out = null; 
    try {
        in = new BufferedInputStream(new FileInputStream(oringen));
        out = new BufferedOutputStream(new FileOutputStream(destino));
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    } finally {
        if(in!=null)
        	in.close();
        if(out!=null)
        	out.close();
    }
}
}
