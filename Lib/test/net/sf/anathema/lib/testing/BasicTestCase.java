package net.sf.anathema.lib.testing;

import junit.framework.TestCase;

import net.disy.commons.core.util.ISimpleBlock;

public abstract class BasicTestCase extends TestCase {

  public static void assertEquals(Object[] expected, Object[] actual) {
    assertEquals(null, expected, actual);
  }

  public static void assertEquals(String message, Object[] expected, Object[] actual) {
    message = (message == null) ? "" : message + " - "; //$NON-NLS-1$//$NON-NLS-2$
    if (expected == null) {
      assertNull(message + "actual is not null", actual); //$NON-NLS-1$
      return;
    }
    assertNotNull(message + "actual is null", actual); //$NON-NLS-1$
    assertEquals(message + "array lengths", expected.length, actual.length); //$NON-NLS-1$
    for (int i = 0; i < expected.length; ++i) {
      assertEquals(message + "at index " + i, expected[i], actual[i]); //$NON-NLS-1$
    }
  }
}