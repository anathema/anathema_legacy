package net.sf.anathema.lib.control.stringvalue;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IStringValueChangedListener extends IObjectValueChangedListener<String>{
  
  public void valueChanged(String newValue);
}