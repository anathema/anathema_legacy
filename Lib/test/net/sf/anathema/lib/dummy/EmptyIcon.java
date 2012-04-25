package net.sf.anathema.lib.dummy;

import javax.swing.*;
import java.awt.*;

public class EmptyIcon implements Icon {

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    // Nothing to do
  }

  @Override
  public int getIconWidth() {
    return 16;
  }

  @Override
  public int getIconHeight() {
    return 16;
  }
}