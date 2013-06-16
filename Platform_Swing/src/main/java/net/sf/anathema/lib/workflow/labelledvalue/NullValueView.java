package net.sf.anathema.lib.workflow.labelledvalue;

import net.sf.anathema.framework.ui.RGBColor;

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
  public void setFontStyle(int style) {
    //nothing to do
  }
}