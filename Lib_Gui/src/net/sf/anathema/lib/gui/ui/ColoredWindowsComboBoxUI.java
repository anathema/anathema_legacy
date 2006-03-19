package net.sf.anathema.lib.gui.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import sun.swing.DefaultLookup;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

public class ColoredWindowsComboBoxUI extends WindowsComboBoxUI {

  private final Color disabledForegroundColor;

  public ColoredWindowsComboBoxUI(Color disabledForegroundColor) {
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

  @Override
  public void paintCurrentValue(Graphics g, Rectangle bounds, boolean focussed) {
    if (isXP()) {
      bounds.x += 2;
      bounds.y += 2;
      bounds.width -= 3;
      bounds.height -= 4;
    }
    else {
      bounds.x += 1;
      bounds.y += 1;
      bounds.width -= 2;
      bounds.height -= 2;
    }
    paintBasicComboBoxUI(g, bounds, focussed);
  }

  private synchronized boolean isXP() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    boolean themeActive = Boolean.TRUE.equals(toolkit.getDesktopProperty("win.xpstyle.themeActive")); //$NON-NLS-1$
    boolean noXP = System.getProperty("swing.noxp") != null; //$NON-NLS-1$
    boolean runsClassic = UIManager.getLookAndFeel() instanceof WindowsClassicLookAndFeel;
    return (themeActive && noXP && !runsClassic);
  }
}