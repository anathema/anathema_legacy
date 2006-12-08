package net.sf.anathema.test.lib.lang.clone;

import net.sf.anathema.lib.lang.clone.ICloneable;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractDeepCloneableTest {

  protected abstract ICloneable< ? > createObjectUnderTest();

  @Test
  public final void testDeepClone() throws Exception {
    ICloneable< ? > cloneable = createObjectUnderTest();
    Object clone = cloneable.clone();
    new DeepCloneChecker().assertDeepClonedIgnoringTransientField(cloneable, clone);
    Assert.assertEquals(cloneable, clone);
  }
}