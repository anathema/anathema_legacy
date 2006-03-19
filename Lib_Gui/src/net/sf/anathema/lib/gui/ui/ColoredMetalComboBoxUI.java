package net.sf.anathema.lib.gui.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import sun.swing.DefaultLookup;

public class ColoredMetalComboBoxUI extends MetalComboBoxUI {

  @Override
  public void paintCurrentValue(Graphics g, Rectangle bounds, boolean focused) {
    if (MetalLookAndFeel.getCurrentTheme() instanceof OceanTheme) {
      bounds.x += 2;
      bounds.y += 2;
      bounds.width -= 3;
      bounds.height -= 4;
      paintBasicComboBoxUI(g, bounds, focused);
    }
    else if (g == null || bounds == null) {
      throw new NullPointerException("Must supply a non-null Graphics and Rectangle"); //$NON-NLS-1$
    }
  }

  private final Color disabledForegroundColor;

  public ColoredMetalComboBoxUI(Color disabledForegroundColor) {
    this.disabledForegroundColor = disabledForegroundColor;
  }

  protected void paintBasicComboBoxUI(Graphics g, Rectangle bounds, boolean focussed) {
    ListCellRenderer renderer = comboBox.getRenderer();
    Component c;

    if (focussed && !isPopupVisible(comboBox)) {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
    }
    else {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
      c.setBackground(UIManager.getColor("ComboBox.background")); //$NON-NLS-1$
    }
    c.setFont(comboBox.getFont());
    if (focussed && !isPopupVisible(comboBox)) {
      c.setForeground(listBox.getSelectionForeground());
      c.setBackground(listBox.getSelectionBackground());
    }
    else {
      if (comboBox.isEnabled()) {
        c.setForeground(comboBox.getForeground());
        c.setBackground(comboBox.getBackground());
      }
      else {
        c.setForeground(disabledForegroundColor);
        c.setBackground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledBackground", null)); //$NON-NLS-1$
      }
    }
    // Fix for 4238829: should lay out the JPanel.
    boolean shouldValidate = false;
    if (c instanceof JPanel) {
      shouldValidate = true;
    }
    currentValuePane.paintComponent(g, c, comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
  }
}