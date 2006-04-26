package net.sf.anathema.lib.testing;

import net.disy.commons.core.testing.CoreTestCase;
import net.disy.commons.core.testing.ExceptionConvertingBlock;

public class BasicTestCase extends CoreTestCase {

  public final static void assertThrowsError(Class errorClass, ExceptionConvertingBlock block) {
    try {
      block.execute();
      fail("expected: " + errorClass.getName() + ", actual: no exception thrown"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    catch (Throwable thrown) {
      assertIsAssignableFrom(errorClass, thrown.getCause().getClass());
    }
  }
}