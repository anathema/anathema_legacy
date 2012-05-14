/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.animation;

import net.disy.commons.swing.dialog.core.IDialogConstants;

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