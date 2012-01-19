package net.sf.anathema.lib.dummy;

import javax.swing.*;
import java.awt.*;

public class EmptyIcon implements Icon {

  public void paintIcon(Component c, Graphics g, int x, int y) {
    // Nothing to do
  }

  public int getIconWidth() {
    return 16;
  }

  public int getIconHeight() {
    return 16;
  }
}