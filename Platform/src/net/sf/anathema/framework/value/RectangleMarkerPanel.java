package net.sf.anathema.framework.value;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RectangleMarkerPanel extends AbstractMarkerPanel {
  private static final long serialVersionUID = 8081094439066347485L;
  private final Rectangle rectangleToDraw = new Rectangle();

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawRect(rectangleToDraw.x, rectangleToDraw.y, rectangleToDraw.width - 1, rectangleToDraw.height - 1);
    g.setColor(new Color(120, 120, 120, 120));
    g.fillRect(rectangleToDraw.x, rectangleToDraw.y, rectangleToDraw.width - 1, rectangleToDraw.height - 1);
  }

  @Override
  public void resizeMarkerRectangle(int width) {
    width = Math.min(width, getWidth());
    rectangleToDraw.setSize(width, getHeight());
    repaint();
  }
}