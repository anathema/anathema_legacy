package net.sf.anathema.character.main.view.labelledvalue;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.control.legality.FontStyle;

public class NullValueView<E> implements IValueView<E>{
  @Override
  public void setValue(E value) {
    //nothing to do
  }

  @Override
  public void setTextColor(RGBColor color) {
    //nothing to do
  }

  @Override
  public void setFontStyle(FontStyle style) {
    //nothing to do
  }
}