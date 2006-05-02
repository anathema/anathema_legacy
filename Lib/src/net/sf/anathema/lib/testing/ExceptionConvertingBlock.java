package net.sf.anathema.lib.testing;

import net.disy.commons.core.util.ISimpleBlock;

public abstract class ExceptionConvertingBlock implements ISimpleBlock {

  public void execute() {
    try {
      executeExceptionThrowing();
    }
    catch (Exception e) {
      throw new NestingRuntimeException(e);
    }
  }

  protected abstract void executeExceptionThrowing() throws Exception;
}