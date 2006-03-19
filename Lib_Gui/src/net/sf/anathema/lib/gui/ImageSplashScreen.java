package net.sf.anathema.lib.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import net.disy.commons.swing.util.GuiUtilities;

/** A simple splashscreen based on a javaworld tutorial. */
public class ImageSplashScreen extends JWindow {

  /**
   * Create the Splashscreen with <code>splashImage</code> as sole source and show it for <code>delay</code> ms or
   * until clicked.
   */
  public ImageSplashScreen(Icon splashImage, final int delay) {
    super(new JFrame());
    JLabel splashLabel = new JLabel(splashImage);
    Dimension splashSize = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());
    splashLabel.setPreferredSize(splashSize);
    this.setSize(splashSize);
    this.setAlwaysOnTop(true);
    getContentPane().add(splashLabel, BorderLayout.CENTER);
    pack();
    GuiUtilities.centerOnScreen(this);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        setVisible(false);
        dispose();
      }
    });
    final Runnable closerRunner = new Runnable() {
      public void run() {
        setVisible(false);
        dispose();
      }
    };
    Runnable waitRunner = new Runnable() {
      public void run() {
        try {
          Thread.sleep(delay);
          SwingUtilities.invokeAndWait(closerRunner);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    };
    setVisible(true);
    Thread splashThread = new Thread(waitRunner, "SplashThread"); //$NON-NLS-1$
    splashThread.start();
  }
}