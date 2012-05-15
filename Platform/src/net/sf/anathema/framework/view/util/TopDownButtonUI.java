package net.sf.anathema.framework.view.util;

import javax.swing.JComponent;
import java.awt.Graphics2D;

public class TopDownButtonUI extends AbstractVerticalButtonUi {

  @Override
  protected void transform(Graphics2D graphics, JComponent component) {
    graphics.rotate(Math.PI / 2);
    graphics.translate(0, -component.getWidth());
  }
}