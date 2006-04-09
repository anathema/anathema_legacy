package net.sf.anathema.lib.workflow.labelledvalue;

import java.awt.Color;

public interface IValueView<E> {

  public void setTextColor(Color color);

  public void setFontStyle(int style);
  
  public void setValue(E value);
}