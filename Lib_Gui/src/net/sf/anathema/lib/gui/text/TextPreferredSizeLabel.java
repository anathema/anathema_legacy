package net.sf.anathema.lib.gui.text;

import com.google.common.base.Preconditions;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FontMetrics;

public class TextPreferredSizeLabel extends JLabel {

  private static final long serialVersionUID = -5431559719484711326L;
private final int verticalPadding;
  private final int horizontalPadding;
  private final String text;

  public TextPreferredSizeLabel(String text) {
    this(text, 0, 0);
  }

  public TextPreferredSizeLabel(String text, int verticalPadding, int horizontalPadding) {
    Preconditions.checkNotNull(text);
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