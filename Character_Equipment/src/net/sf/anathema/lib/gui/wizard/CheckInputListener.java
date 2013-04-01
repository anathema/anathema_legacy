package net.sf.anathema.lib.gui.wizard;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;

public class CheckInputListener implements
    IChangeListener,
    IBooleanValueChangedListener, ObjectValueListener<String> {

  private final Runnable executionBlock;

  public CheckInputListener(Runnable executionBlock) {
    this.executionBlock = executionBlock;
  }

  @Override
  public void changeOccurred() {
    executionBlock.run();
  }

  @Override
  public void valueChanged(boolean newValue) {
    executionBlock.run();
  }

  @Override
  public void valueChanged(String newValue) {
    executionBlock.run();
  }
}