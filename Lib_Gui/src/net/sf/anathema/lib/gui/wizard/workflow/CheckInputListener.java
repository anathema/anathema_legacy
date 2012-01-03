package net.sf.anathema.lib.gui.wizard.workflow;

import net.disy.commons.core.util.SimpleBlock;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class CheckInputListener implements
    IChangeListener,
    IBooleanValueChangedListener,
    IObjectValueChangedListener<String> {

  private final SimpleBlock executionBlock;

  public CheckInputListener(SimpleBlock executionBlock) {
    this.executionBlock = executionBlock;
  }

  public void changeOccured() {
    executionBlock.execute();
  }

  public void valueChanged(boolean newValue) {
    executionBlock.execute();
  }

  public void valueChanged(String newValue) {
    executionBlock.execute();
  }
}