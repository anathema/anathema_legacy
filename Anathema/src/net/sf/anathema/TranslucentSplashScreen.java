package net.sf.anathema;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class TranslucentSplashScreen extends JWindow {
  private final ImagePanel imagePanel = new ImagePanel();
  private final JPanel panel = new JPanel(new BorderLayout());
  private final BufferedImage screenImage;
  private final Dimension screenSize;

  public TranslucentSplashScreen(Image image) throws AWTException {
    Icon icon = new ImageIcon(image);
    this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.screenImage = new Robot().createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
    this.setContentPane(imagePanel);
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentMoved(ComponentEvent e) {
        changeBackgroundImage();
        repaint();
      }
    });
    getContentPane().add(panel);
    getContentPane().add(new JLabel(icon));
    Dimension splashSize = new Dimension(icon.getIconWidth(), icon.getIconHeight());
    setSize(splashSize);
    centerOnScreen();
  }

  public void showSplash(final int delay) {
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

  private void centerOnScreen() {
    Rectangle rectangle = new Rectangle(getGraphicsConfiguration().getBounds());
    Dimension size = getSize();
    int x1 = ((rectangle.x * 2 + rectangle.width) / 2) - size.width / 2;
    int y1 = ((rectangle.y * 2 + rectangle.height) / 2) - size.height / 2;
    x1 = x1 < 0 ? 0 : x1;
    y1 = y1 < 0 ? 0 : y1;
    setLocation(x1, y1);
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