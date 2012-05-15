package net.sf.anathema.lib.gui.dialog.animation;

import net.sf.anathema.lib.gui.dialog.core.IDialogConstants;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

public class OverlaidComponentBorder implements Border {

  private static final Insets INSETS = new Insets(2, 2, 0, 2);

  @Override
  public Insets getBorderInsets(final Component c) {
    return INSETS;
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }

  @Override
  public void paintBorder(
      final Component c,
      final Graphics g,
      final int x,
      final int y,
      final int width,
      final int height) {
    g.setColor(IDialogConstants.HEADER_OVERLAID_BORDER_COLOR);
    g.drawLine(x + 2, y, x + width - 3, y);
    g.drawLine(x + 1, y + 1, x + 1, y + 1);
    g.drawLine(x + width - 2, y + 1, x + width - 2, y + 1);
    g.drawLine(x, y + 2, x, y + height);
    g.drawLine(x + width - 1, y + 2, x + width - 1, y + height);
  }
}