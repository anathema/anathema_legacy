package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;


public class PrintNameAdjuster implements IStringValueChangedListener {
  
  private final IItem item;

  public PrintNameAdjuster(IItem item) {
    this.item = item;
  }

  public void valueChanged(String newValue) {
    item.setPrintName(newValue);
  }
}