package net.sf.anathema.lib.gui.wizard.workflow;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CheckInputListener implements IChangeListener {

  private final ISimpleBlock executionBlock;

  public CheckInputListener(ISimpleBlock executionBlock) {
    this.executionBlock = executionBlock;
  }

  public void changeOccured() {
    executionBlock.execute();
  }
}