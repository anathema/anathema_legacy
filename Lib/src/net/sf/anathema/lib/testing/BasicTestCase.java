package net.sf.anathema.lib.testing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.AssertionFailedError;
import net.disy.commons.core.testing.CoreTestCase;
import net.disy.commons.core.testing.ExceptionConvertingBlock;

public class BasicTestCase extends CoreTestCase {

  protected static void assertNotEquals(Object first, Object second) {
    if (first == null) {
      if (second == null) {
        throw new AssertionFailedError("Both objects are 'null'."); //$NON-NLS-1$
      }
    }
    else {
      if (first.equals(second)) {
        throw new AssertionFailedError("Both objects are equal."); //$NON-NLS-1$
      }
    }
  }

  public static void assertEquals(int[] expected, int[] actual) {
    assertEquals(null, expected, actual);
  }

  public static void assertEquals(String message, int[] expected, int[] actual) {
    if (expected == null) {
      assertNull(message, actual);
      return;
    }
    assertNotNull(message, actual);
    assertEquals(concatMessage(message, "array-length: "), expected.length, actual.length); //$NON-NLS-1$
    for (int i = 0; i < expected.length; ++i) {
      assertEquals(concatMessage(message, "index: " + i), expected[i], actual[i]); //$NON-NLS-1$
    }
  }

  private static String concatMessage(String message, String addition) {
    return (message == null ? "" : message + "; ") + (addition); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public static Object serializeAndDeserialize(Object object) throws IOException, ClassNotFoundException {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(byteOut);
    out.writeObject(object);
    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
    ObjectInputStream in = new ObjectInputStream(byteIn);
    return in.readObject();
  }

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