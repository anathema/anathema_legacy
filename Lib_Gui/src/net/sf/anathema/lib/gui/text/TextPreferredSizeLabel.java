package net.sf.anathema.lib.gui.text;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JLabel;

import net.disy.commons.core.util.Ensure;

public class TextPreferredSizeLabel extends JLabel {

  private final int verticalPadding;
  private final int horizontalPadding;
  private final String text;

  public TextPreferredSizeLabel(String text) {
    this(text, 0, 0);
  }

  public TextPreferredSizeLabel(String text, int verticalPadding, int horizontalPadding) {
    Ensure.ensureArgumentNotNull(text);
    this.verticalPadding = verticalPadding;
    this.horizontalPadding = horizontalPadding;
    this.text = text;
  }

  @Override
  public java.awt.Dimension getPreferredSize() {
    FontMetrics fontMetrics = getFontMetrics(getFont());
    return new Dimension(fontMetrics.stringWidth(text) + horizontalPadding, fontMetrics.getHeight() + verticalPadding);
  }
}