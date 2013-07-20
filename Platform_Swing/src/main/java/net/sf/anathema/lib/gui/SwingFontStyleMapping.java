package net.sf.anathema.lib.gui;

import net.sf.anathema.framework.ui.FontStyle;

import java.awt.Font;

public class SwingFontStyleMapping {

  public static int map(FontStyle style){
    if (FontStyle.Bold == style) {
      return Font.BOLD;
    }
    else {
      return Font.PLAIN;
    }
  }
}
