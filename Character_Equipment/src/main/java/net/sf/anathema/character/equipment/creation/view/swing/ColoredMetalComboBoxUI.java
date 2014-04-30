package net.sf.anathema.character.equipment.creation.view.swing;

import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ColoredMetalComboBoxUI extends MetalComboBoxUI {

  private final Color disabledForegroundColor;

  public ColoredMetalComboBoxUI(Color disabledForegroundColor) {
    this.disabledForegroundColor = disabledForegroundColor;
  }

  @Override
  public void paintCurrentValue(Graphics g, Rectangle bounds, boolean focused) {
    if (MetalLookAndFeel.getCurrentTheme() instanceof OceanTheme) {
      bounds.x += 2;
      bounds.y += 2;
      bounds.width -= 3;
      bounds.height -= 4;
      new ComboBoxPainter(disabledForegroundColor, comboBox, listBox, currentValuePane).paintBasicComboBoxUI(
          g,
          bounds,
          focused,
          isPopupVisible(comboBox),
          this);
    }
  }
}