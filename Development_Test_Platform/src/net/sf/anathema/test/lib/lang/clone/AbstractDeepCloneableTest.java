package net.sf.anathema.test.lib.lang.clone;

import net.sf.anathema.lib.lang.clone.ICloneable;
import net.sf.anathema.lib.testing.BasicTestCase;

public abstract class AbstractDeepCloneableTest extends BasicTestCase {

  protected abstract ICloneable<?> createObjectUnderTest();

  public final void testDeepClone() throws Exception {
    ICloneable<?> cloneable = createObjectUnderTest();
    Object clone = cloneable.clone();
    new DeepCloneChecker().assertDeepClonedIgnoringTransientField(cloneable, clone);
    assertEquals(cloneable, clone);
  }
}