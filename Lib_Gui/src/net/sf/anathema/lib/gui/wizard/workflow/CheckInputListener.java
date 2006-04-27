package net.sf.anathema.lib.gui.wizard.workflow;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class CheckInputListener implements IChangeListener, IObjectValueChangedListener<String> {

  private final ISimpleBlock executionBlock;

  public CheckInputListener(ISimpleBlock executionBlock) {
    this.executionBlock = executionBlock;
  }

  public void changeOccured() {
    executionBlock.execute();
  }

  public void valueChanged(String newValue) {
    executionBlock.execute();
  }
}