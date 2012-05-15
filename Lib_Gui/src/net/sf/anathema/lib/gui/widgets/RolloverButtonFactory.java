/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.widgets;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.swing.SwingColors;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Dimension;
import java.awt.Graphics;

public class RolloverButtonFactory {

  public static JButton createButton(final SmartAction action) {
    final JButton button = new JButton(action) {
      @Override
      public void setUI(final ButtonUI ui) {
        super.setUI(new BasicButtonUI() {
          @Override
          public void paint(final Graphics g, final JComponent c) {
            super.paint(g, c);
            paintButtonBorder(g, (AbstractButton) c);
          }
        });
      }
    };
    adjustButtonSizeAndBehavior(action, button);
    return button;
  }

  private static void adjustButtonSizeAndBehavior(final Action action, final AbstractButton button) {
    final Icon icon = (Icon) action.getValue(Action.SMALL_ICON);
    final String name = (String) action.getValue(Action.NAME);
    if (icon != null && name == null) {
      button.setPreferredSize(new Dimension(icon.getIconWidth() + 7, icon.getIconHeight() + 7));
    }
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setRolloverEnabled(true);
  }

  private static void paintButtonBorder(final Graphics g, final AbstractButton button) {
    if (button.getModel().isSelected()
        || button.getModel().isPressed()
        && button.getModel().isArmed()) {
      final Dimension size = button.getSize();
      drawSmall3dRectangleDown(g, 0, 0, size.width, size.height);
    }
    else if (button.isRolloverEnabled() && button.getModel().isRollover()) {
      final Dimension size = button.getSize();
      drawSmall3dRectangleUp(g, 0, 0, size.width, size.height);
    }
  }

  private static void drawSmall3dRectangleDown(
      final Graphics g,
      final int x,
      final int y,
      final int w,
      final int h) {
    g.setColor(SwingColors.getControlLtHighlightColor());
    g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
    g.drawLine(x + w - 2, y + h - 1, x, y + h - 1);
    g.setColor(SwingColors.getControlShadowColor());
    g.drawLine(x, y, x + w - 2, y);
    g.drawLine(x, y + 1, x, y + h - 2);
  }

  private static void drawSmall3dRectangleUp(
      final Graphics g,
      final int x,
      final int y,
      final int w,
      final int h) {
    g.setColor(SwingColors.getControlLtHighlightColor());
    g.drawLine(x, y, x + w - 2, y);
    g.drawLine(x, y + 1, x, y + h - 2);
    g.setColor(SwingColors.getControlShadowColor());
    g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
    g.drawLine(x + w - 1, y + h - 1, x, y + h - 1);
  }
}