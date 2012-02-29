package net.sf.anathema.framework.view.item;

import javax.swing.text.StyleContext;
import java.awt.Font;

class FontHolder {
  public static final Font TAB_CAPTION_FONT = createCompositeFont(Font.PLAIN, 11);

  private static Font createCompositeFont(int style, int size) {
    return StyleContext.getDefaultStyleContext().getFont(Font.SANS_SERIF, style, size);
  }
}
