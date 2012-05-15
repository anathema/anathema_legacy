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
    JButton button = new JButton(action) {
      @Override
      public void setUI(ButtonUI ui) {
        super.setUI(new BasicButtonUI() {
          @Override
          public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            paintButtonBorder(g, (AbstractButton) c);
          }
        });
      }
    };
    adjustButtonSizeAndBehavior(action, button);
    return button;
  }

  private static void adjustButtonSizeAndBehavior(Action action, AbstractButton button) {
    Icon icon = (Icon) action.getValue(Action.SMALL_ICON);
    String name = (String) action.getValue(Action.NAME);
    if (icon != null && name == null) {
      button.setPreferredSize(new Dimension(icon.getIconWidth() + 7, icon.getIconHeight() + 7));
    }
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setRolloverEnabled(true);
  }

  private static void paintButtonBorder(Graphics g, AbstractButton button) {
    if (button.getModel().isSelected() || button.getModel().isPressed() && button.getModel().isArmed()) {
      Dimension size = button.getSize();
      drawSmall3dRectangleDown(g, 0, 0, size.width, size.height);
    } else if (button.isRolloverEnabled() && button.getModel().isRollover()) {
      Dimension size = button.getSize();
      drawSmall3dRectangleUp(g, 0, 0, size.width, size.height);
    }
  }

  private static void drawSmall3dRectangleDown(Graphics g, int x, int y, int w, int h) {
    g.setColor(SwingColors.getControlLtHighlightColor());
    g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
    g.drawLine(x + w - 2, y + h - 1, x, y + h - 1);
    g.setColor(SwingColors.getControlShadowColor());
    g.drawLine(x, y, x + w - 2, y);
    g.drawLine(x, y + 1, x, y + h - 2);
  }

  private static void drawSmall3dRectangleUp(Graphics g, int x, int y, int w, int h) {
    g.setColor(SwingColors.getControlLtHighlightColor());
    g.drawLine(x, y, x + w - 2, y);
    g.drawLine(x, y + 1, x, y + h - 2);
    g.setColor(SwingColors.getControlShadowColor());
    g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
    g.drawLine(x + w - 1, y + h - 1, x, y + h - 1);
  }
}