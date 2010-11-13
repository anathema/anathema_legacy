package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class PrintNameAdjuster implements IObjectValueChangedListener<String> {

  private final IItem item;

  public PrintNameAdjuster(IItem item) {
    this.item = item;
  }

  public void valueChanged(String newValue) {
    item.setPrintName(newValue);
  }
}