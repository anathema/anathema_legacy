package net.sf.anathema.platform.tree.view.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TextWriter {

  private final String text;
  private final Color textColor;
  private final Rectangle bounds;

  public TextWriter(Rectangle bounds, Color textColor, String text) {
    this.bounds = bounds;
    this.textColor = textColor;
    this.text = text;

  }

  public void write(Graphics2D graphics) {
    graphics.setColor(textColor);
    Font textFont = new Font("SansSerif", Font.PLAIN, 15);
    graphics.setFont(textFont);
    FontMetrics textMetrics = graphics.getFontMetrics(textFont);
    int centeredX = (int) (bounds.x + bounds.getWidth() / 2) - (textMetrics.stringWidth(text) / 2);
    int centeredY = (int) (bounds.y + bounds.getHeight() / 2) + (textMetrics.getHeight() / 2);
    graphics.drawString(text, centeredX, centeredY);
  }
}
