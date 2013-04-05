package net.sf.anathema.lib.gui.widgets;

import net.sf.anathema.lib.gui.swing.SwingColors;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * A horizontal line that can be used for visual structuring of gui layouts.
 */
public class HorizontalLine extends JComponent {

  private final Insets margin = new Insets(0, 0, 0, 0);

  public HorizontalLine() {
    this(100);
  }

  public HorizontalLine(int preferredWidth) {
    setPreferredSize(new Dimension(preferredWidth, 2));
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension dimension = super.getPreferredSize();
    return new Dimension(dimension.width + margin.left + margin.right, dimension.height + margin.top + margin.bottom);
  }

  @Override
  protected void paintComponent(Graphics g) {
    int y = margin.top + (getSize().height - 2 - margin.top - margin.bottom) / 2;
    g.setColor(SwingColors.getControlShadowColor());
    g.drawLine(margin.left, y, getSize().width - margin.right, y);
    g.setColor(SwingColors.getControlLtHighlightColor());
    g.drawLine(margin.left, y + 1, getSize().width - margin.right, y + 1);
  }
}