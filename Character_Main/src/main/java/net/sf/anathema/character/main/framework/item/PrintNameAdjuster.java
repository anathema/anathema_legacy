package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.lib.control.ObjectValueListener;

public class PrintNameAdjuster implements ObjectValueListener<String> {

  private final Item item;

  public PrintNameAdjuster(Item item) {
    this.item = item;
  }

  @Override
  public void valueChanged(String newValue) {
    item.setPrintName(newValue);
  }
}