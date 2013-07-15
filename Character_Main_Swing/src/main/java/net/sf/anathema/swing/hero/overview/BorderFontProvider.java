package net.sf.anathema.swing.hero.overview;

import java.awt.Font;

public class BorderFontProvider {

  private final FontTitledBorder border = new FontTitledBorder("");

  public Font getFont() {
    return border.getFont(null);
  }
}