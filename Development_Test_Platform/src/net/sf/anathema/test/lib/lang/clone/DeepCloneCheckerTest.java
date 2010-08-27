package net.sf.anathema.test.lib.lang.clone;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

public class DeepCloneCheckerTest {

  private DeepCloneChecker cloneChecker;

  @SuppressWarnings("unused")
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

  @Before
  public void setUp() throws Exception {
    this.cloneChecker = new DeepCloneChecker();
  }

  @Test
  public void testTransientField() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new TransientPrimitiveField(1), new TransientPrimitiveField(2));
  }

  @Test
  public void testStaticField() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new StaticPrimitiveField(), new StaticPrimitiveField());
  }

  @Test(expected = AssertionFailedError.class)
  public void testExceptionUnmatchedPrimitveFields() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new PrimitiveIntegerField(1), new PrimitiveIntegerField(2));
  }

  @Test(expected = AssertionFailedError.class)
  public void testExceptionUnmatchedUnclonedReferenceFields() throws Exception {
    PrimitiveIntegerField referenceField = new PrimitiveIntegerField(1);
    cloneChecker.assertDeepClonedIgnoringTransientField(
        new ForeignReferenceField(referenceField),
        new ForeignReferenceField(referenceField));
  }

  @Test(expected = AssertionFailedError.class)
  public void testExceptionOnUnmatchedSuperclassField() throws Exception {
    cloneChecker.assertDeepClonedIgnoringTransientField(new ExtendedClass(0), new ExtendedClass(1));
  }
}