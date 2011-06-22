package net.sf.anathema.test.lib.lang.clone;

import java.awt.Color;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

public class DeepCloneChecker {

  @SuppressWarnings("serial")
  private List<Class<?>> checkEqualsClasses = new ArrayList<Class<?>>() {
    {
      add(Byte.class);
      add(Double.class);
      add(Float.class);
      add(Integer.class);
      add(Long.class);
      add(Short.class);
      add(Boolean.class);
      add(String.class);
      add(Color.class);
    }
  };

  public void assertDeepClonedIgnoringTransientField(Object first, Object second) throws Exception {
    Class< ? extends Object> classObject = first.getClass();
    Assert.assertSame(classObject, second.getClass());
    Assert.assertNotSame(first, second);
    while (classObject != null) {
      checkField(classObject.getDeclaredFields(), first, second);
      classObject = classObject.getSuperclass();
    }
  }

  private void checkField(Field[] fields, Object first, Object second) throws Exception {
    AccessibleObject.setAccessible(fields, true);
    for (int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++) {
      Field currentField = fields[fieldIndex];
      checkField(currentField, currentField.get(first), currentField.get(second));
    }
  }

  private void checkField(Field currentField, Object first, Object second) throws Exception {
    int modifiers = currentField.getModifiers();
    if (Modifier.isTransient(modifiers) || Modifier.isStatic(modifiers)) {
      return;
    }
    if (first == null) {
      Assert.assertNull("Must be null " + currentField, second); //$NON-NLS-1$
      return;
    }
    Class< ? extends Object> firstClazz = first.getClass();
    Assert.assertNotNull("Must not be null " + currentField, second); //$NON-NLS-1$
    Assert.assertSame(firstClazz, second.getClass());
    if (currentField.isEnumConstant() || checkEqualsClasses.contains(firstClazz)) {
      Assert.assertEquals("Enum " + currentField, first, second); //$NON-NLS-1$
      return;
    }
    Assert.assertNotSame("Field " + currentField, first, second); //$NON-NLS-1$
    assertDeepClonedIgnoringTransientField(first, second);
  }
}