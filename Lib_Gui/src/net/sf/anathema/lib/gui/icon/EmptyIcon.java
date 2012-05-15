package net.sf.anathema.lib.gui.icon;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class EmptyIcon implements Icon {

  public static final Icon DEFAULT_ICON = new EmptyIcon(new Dimension(16, 16));

  private final Dimension size;

  public EmptyIcon() {
    this(new Dimension(0, 0));
  }

  public EmptyIcon(Dimension size) {
    this.size = size;
  }

  @Override
  public int getIconHeight() {
    return size.height;
  }

  @Override
  public int getIconWidth() {
    return size.width;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    //nothing to do
  }
}
