package net.sf.anathema.framework.view.util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TopDownButtonUI extends AbstractVerticalButtonUi {

  @Override
  protected void transform(Graphics2D graphics, JComponent component) {
    graphics.rotate(Math.PI / 2);
    graphics.translate(0, -component.getWidth());
  }
}