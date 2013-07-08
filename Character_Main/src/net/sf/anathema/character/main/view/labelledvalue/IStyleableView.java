package net.sf.anathema.character.main.view.labelledvalue;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.control.legality.FontStyle;

public interface IStyleableView {

  void setTextColor(RGBColor color);

  void setFontStyle(FontStyle style);
}