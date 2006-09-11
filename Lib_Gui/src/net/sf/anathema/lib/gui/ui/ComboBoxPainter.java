package net.sf.anathema.lib.gui.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.CellRendererPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import sun.swing.DefaultLookup;

public class ComboBoxPainter {

  private final Color disabledForegroundColor;
  private final JComboBox comboBox;
  private final JList listBox;
  private final CellRendererPane currentValuePane;

  public ComboBoxPainter(Color disabledForegroundColor, JComboBox comboBox, JList listBox, CellRendererPane currentValuePane) {
    this.disabledForegroundColor = disabledForegroundColor;
    this.comboBox = comboBox;
    this.listBox = listBox;
    this.currentValuePane = currentValuePane;
  }

  public void paintBasicComboBoxUI(Graphics g, Rectangle bounds, boolean focussed, boolean popUpVisible, ComponentUI ui) {
    ListCellRenderer renderer = comboBox.getRenderer();
    Component c;

    if (focussed && !popUpVisible) {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
    }
    else {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
      c.setBackground(UIManager.getColor("ComboBox.background")); //$NON-NLS-1$
    }
    c.setFont(comboBox.getFont());
    if (focussed && !popUpVisible) {
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
        c.setBackground(DefaultLookup.getColor(comboBox, ui, "ComboBox.disabledBackground", null)); //$NON-NLS-1$
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