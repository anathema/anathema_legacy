package net.sf.anathema.lib.gui.icon;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DisabledIconDecorator implements Icon {

  private final Icon icon;

  public DisabledIconDecorator(final Icon icon) {
    this.icon = icon;
  }

  @Override
  public int getIconHeight() {
    return icon.getIconHeight();
  }

  @Override
  public int getIconWidth() {
    return icon.getIconWidth();
  }

  @Override
  public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
    final ColorFilterGraphics disabledGraphics = new ColorFilterGraphics((Graphics2D) g, c);
    icon.paintIcon(c, disabledGraphics, x, y);
  }

}
