package net.sf.anathema.lib.testing;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.lib.exception.NestedRuntimeException;

public abstract class ExceptionConvertingBlock implements ISimpleBlock {

  public void execute() {
    try {
      executeExceptionThrowing();
    }
    catch (Exception e) {
      throw new NestedRuntimeException(e);
    }
  }

  protected abstract void executeExceptionThrowing() throws Exception;
}