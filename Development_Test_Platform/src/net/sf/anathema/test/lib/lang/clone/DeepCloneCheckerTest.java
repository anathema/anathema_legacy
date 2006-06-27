package net.sf.anathema.test.lib.lang.clone;

import junit.framework.AssertionFailedError;
import net.sf.anathema.lib.testing.BasicTestCase;

public class DeepCloneCheckerTest extends BasicTestCase {

  private DeepCloneChecker cloneChecker;

  private static class StaticPrimitiveField {
    public static final int CONSTANT = 2;
  }

  static class TransientPrimitiveField {
    private final transient int transientField;

    public TransientPrimitiveField(int value) {
      this.transientField = value;
    }
    
    public int getTransientField() {
      return transientField;
    }
  }

  static class PrimitiveIntegerField {
    private final int field;

    public PrimitiveIntegerField(int value) {
      this.field = value;
    }
    
    public int getField() {
      return field;
    }
  }

  static class ForeignReferenceField {
    private final Object field;

    public ForeignReferenceField(Object field) {
      this.field = field;
    }
    
    public Object getField() {
      return field;
    }
  }

  static class ExtendedClass extends PrimitiveIntegerField {
    public ExtendedClass(int value) {
      super(value);
    }
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.cloneChecker = new DeepCloneChecker();
  }

  public void testTransientField() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new TransientPrimitiveField(1), new TransientPrimitiveField(2));
  }

  public void testStaticField() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new StaticPrimitiveField(), new StaticPrimitiveField());
  }

  public void testExceptionUnmatchedPrimitveFields() throws Exception {
    try {
      cloneChecker.assertDeepClonedIgnoringTransientField(new PrimitiveIntegerField(1), new PrimitiveIntegerField(2));
      fail();
    }
    catch (AssertionFailedError ignored) {
      //Nothing to do
    }
  }

  public void testExceptionUnmatchedUnclonedReferenceFields() throws Exception {
    PrimitiveIntegerField referenceField = new PrimitiveIntegerField(1);
    boolean deepCloneCheckFailed = true;
    try {
      cloneChecker.assertDeepClonedIgnoringTransientField(
          new ForeignReferenceField(referenceField),
          new ForeignReferenceField(referenceField));
      deepCloneCheckFailed = false;
    }
    catch (AssertionFailedError ignored) {
      //Nothing to do
    }
    assertTrue(deepCloneCheckFailed);
  }

  public void testExceptionOnUnmatchedSuperclassField() throws Exception {
    boolean deepCloneCheckFailed = true;
    try {
      cloneChecker.assertDeepClonedIgnoringTransientField(new ExtendedClass(0), new ExtendedClass(1));
      deepCloneCheckFailed = false;
    }
    catch (AssertionFailedError ignored) {
      //Nothing to do
    }
    assertTrue(deepCloneCheckFailed);
  }
}