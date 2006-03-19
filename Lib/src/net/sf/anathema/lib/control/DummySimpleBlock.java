package net.sf.anathema.lib.control;

import net.disy.commons.core.util.ISimpleBlock;


public class DummySimpleBlock implements ISimpleBlock {

  private boolean executed = false;

  public DummySimpleBlock() {
    super();
  }

  public void execute() {
    executed = true;
  }

  public boolean wasPerformed() {
    return executed;
  }
}
