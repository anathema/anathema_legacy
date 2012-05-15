package net.sf.anathema.framework.view.util;

import javax.swing.JComponent;
import java.awt.Graphics2D;

public class BottomUpButtonUI extends AbstractVerticalButtonUi {

  protected void transform(Graphics2D graphics, JComponent component) {
    graphics.rotate(-Math.PI / 2);
    graphics.translate(-component.getHeight(), 0);
  }
}