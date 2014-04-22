package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;

public class CheckInputListener implements ChangeListener,
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