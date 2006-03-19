package net.sf.anathema.lib.gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import net.disy.commons.swing.util.GuiUtilities;

public class TranslucentSplashScreen extends JWindow {
  final Robot robot;
  BufferedImage screenImage;
  ImagePanel imagePanel = new ImagePanel();
  boolean userActivate = false;
  Dimension screenSize;
  JPanel panel = new JPanel(new BorderLayout());

  public TranslucentSplashScreen(Icon splashIcon, final int delay) throws AWTException {
    super();
    robot = new Robot();
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenImage = createScreenImage();
    this.setContentPane(imagePanel);
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentMoved(ComponentEvent e) {
        changeBackgroundImage();
        repaint();
      }
    });
    getContentPane().add(panel);
    getContentPane().add(new JLabel(splashIcon));
    Dimension splashSize = new Dimension(splashIcon.getIconWidth(), splashIcon.getIconHeight());
    setSize(splashSize);
    GuiUtilities.centerOnScreen(this);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        setVisible(false);
        dispose();
      }
    });
    Runnable waitRunner = new Runnable() {
      public void run() {
        try {
          Thread.sleep(delay);
          SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
              setVisible(false);
              dispose();
            }
          });
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
    new Thread(waitRunner, "SplashThread").start(); //$NON-NLS-1$
  }

  private BufferedImage createScreenImage() {
    return robot.createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
  }

  private void changeBackgroundImage() {
    Rectangle frameRect = getBounds();
    int x = frameRect.x;
    imagePanel.setPaintX(0);
    if (x < 0) {
      imagePanel.setPaintX(-x);
      x = 0;
    }
    imagePanel.setPaintY(0);
    int y = frameRect.y;
    if (y < 0) {
      imagePanel.setPaintY(-y);
      y = 0;
    }
    int w = frameRect.width;
    if (x + w > screenImage.getWidth()) {
      w = screenImage.getWidth() - x;
    }
    int h = frameRect.height;
    if (y + h > screenImage.getHeight()) {
      h = screenImage.getHeight() - y;
    }
    imagePanel.setBackgroundImage(screenImage.getSubimage(x, y, w, h));
  }
}

class ImagePanel extends JPanel {
  private BufferedImage backgroundImage;
  private int paintX = 0;
  private int paintY = 0;

  public ImagePanel() {
    super();
    setOpaque(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, paintX, paintY, null);
  }

  public void setBackgroundImage(BufferedImage underFrameImg) {
    this.backgroundImage = underFrameImg;
  }

  public void setPaintX(int paintX) {
    this.paintX = paintX;
  }

  public void setPaintY(int paintY) {
    this.paintY = paintY;
  }
}