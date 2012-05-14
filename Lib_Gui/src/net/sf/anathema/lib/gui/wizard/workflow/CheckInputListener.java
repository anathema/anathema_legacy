package net.sf.anathema.lib.gui.wizard.workflow;

import net.disy.commons.core.util.SimpleBlock;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;

public class CheckInputListener implements
    IChangeListener,
    IBooleanValueChangedListener, ObjectValueListener<String> {

  private final SimpleBlock executionBlock;

  public CheckInputListener(SimpleBlock executionBlock) {
    this.executionBlock = executionBlock;
  }

  @Override
  public void changeOccurred() {
    executionBlock.execute();
  }

  @Override
  public void valueChanged(boolean newValue) {
    executionBlock.execute();
  }

  @Override
  public void valueChanged(String newValue) {
    executionBlock.execute();
  }
}