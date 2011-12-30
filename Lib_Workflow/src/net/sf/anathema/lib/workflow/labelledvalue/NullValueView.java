package net.sf.anathema.lib.workflow.labelledvalue;

import java.awt.*;

public class NullValueView<E> implements IValueView<E>{
  @Override
  public void setValue(E value) {
    //nothing to do
  }

  @Override
  public void setTextColor(Color color) {
    //nothing to do
  }

  @Override
  public void setFontStyle(int style) {
    //nothing to do
  }
}