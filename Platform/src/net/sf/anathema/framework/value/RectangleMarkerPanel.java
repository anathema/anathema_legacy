package net.sf.anathema.framework.value;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RectangleMarkerPanel extends JPanel {

  private final Rectangle rectangleToDraw = new Rectangle();

  public RectangleMarkerPanel() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawRect(rectangleToDraw.x, rectangleToDraw.y, rectangleToDraw.width - 1, rectangleToDraw.height - 1);
    g.setColor(new Color(120, 120, 120, 120));
    g.fillRect(rectangleToDraw.x, rectangleToDraw.y, rectangleToDraw.width - 1, rectangleToDraw.height - 1);
  }

  public void resizeMarkerRectangle(int width) {
    width = Math.min(width, getWidth());
    rectangleToDraw.setSize(width, getHeight());
    repaint();
  }
}