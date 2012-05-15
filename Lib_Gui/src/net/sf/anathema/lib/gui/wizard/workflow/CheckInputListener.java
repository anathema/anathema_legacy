package net.sf.anathema.lib.gui.wizard.workflow;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.SimpleBlock;

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