package net.sf.anathema.lib.testing;

import junit.framework.TestCase;

import net.disy.commons.core.util.ISimpleBlock;

public class BasicTestCase extends TestCase {

  public final static <T> void assertThrowsException(Class<T> exceptionClass, ISimpleBlock block) {
    try {
      block.execute();
      fail("expected: " + exceptionClass.getName() + ", actual: no exception thrown"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    catch (Exception thrown) {
      assertIsAssignableFrom(exceptionClass, thrown.getClass());
    }
  }

  public final static <T> void assertThrowsException(Class<T> exceptionClass, ExceptionConvertingBlock block) {
    try {
      block.execute();
      fail("expected: " + exceptionClass.getName() + ", actual: no exception thrown"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    catch (Exception thrown) {
      assertIsAssignableFrom(exceptionClass, thrown.getCause().getClass());
    }
  }

  public final static <T> void assertIsAssignableFrom(Class<T> expected, Class actual) {
    assertTrue("expected: " + expected + ", actual: " + actual, expected.isAssignableFrom(actual)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public final static void assertEquals(Object[] expected, Object[] actual) {
    assertEquals(null, expected, actual);
  }

  public final static void assertEquals(String message, Object[] expected, Object[] actual) {
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