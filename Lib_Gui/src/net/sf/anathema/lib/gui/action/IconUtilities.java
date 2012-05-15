package net.sf.anathema.lib.gui.action;

import javax.swing.Icon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class IconUtilities {

  public static BufferedImage createBufferedImage(Icon icon) {
    BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = image.createGraphics();
    icon.paintIcon(null, graphics, 0, 0);
    graphics.dispose();
    return image;
  }
}