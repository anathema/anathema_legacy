package net.sf.anathema.lib.control.legality;

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
