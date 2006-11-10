package net.sf.anathema.lib.gui.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.UIManager;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

public class ColoredWindowsComboBoxUI extends WindowsComboBoxUI {

  private final Color disabledForegroundColor;

  public ColoredWindowsComboBoxUI(Color disabledForegroundColor) {
    this.disabledForegroundColor = disabledForegroundColor;
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
    new ComboBoxPainter(disabledForegroundColor, comboBox, listBox, currentValuePane).paintBasicComboBoxUI(
        g,
        bounds,
        focussed,
        isPopupVisible(comboBox),
        this);
  }

  private boolean isXP() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    boolean themeActive = Boolean.TRUE.equals(toolkit.getDesktopProperty("win.xpstyle.themeActive")); //$NON-NLS-1$
    boolean noXP = System.getProperty("swing.noxp") != null; //$NON-NLS-1$
    boolean runsClassic = UIManager.getLookAndFeel() instanceof WindowsClassicLookAndFeel;
    return themeActive && noXP && !runsClassic;
  }
}