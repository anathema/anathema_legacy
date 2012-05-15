package net.sf.anathema.lib.collection;

import net.sf.anathema.lib.util.CastingTransformer;
import net.sf.anathema.lib.util.IPredicate;
import net.sf.anathema.lib.util.ITransformer;
import net.sf.anathema.lib.util.ObjectUtilities;

import java.lang.reflect.Array;

public class ArrayUtilities {

  @SuppressWarnings("unchecked")
  public static <T> T[] concat(Class<T> clazz, T[] array1, T... array2) {
    if (array2 == null) {
      return array1;
    }
    if (array1 == null) {
      return array2;
    }
    T[] mergedArray = (T[]) Array.newInstance(clazz, array1.length + array2.length);
    System.arraycopy(array1, 0, mergedArray, 0, array1.length);
    System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
    return mergedArray;
  }

  public static <T> boolean containsValue(T[] array, final T value) {
    return contains(array, new IPredicate<T>() {
      @Override
      public boolean evaluate(T actualValue) {
        return ObjectUtilities.equals(value, actualValue);
      }
    });
  }

  public static <T> boolean contains(T[] array, IPredicate<T> predicate) {
    for (T element : array) {
      if (predicate.evaluate(element)) {
        return true;
      }
    }
    return false;
  }

  public static <T> T getFirst(T[] array, IPredicate<T> predicate) {
    T notFoundValue = null;
    return getFirst(array, predicate, notFoundValue);
  }

  public static <T> T getFirst(T[] array, IPredicate<T> predicate, T notFoundValue) {
    for (T element : array) {
      if (predicate.evaluate(element)) {
        return element;
      }
    }
    return notFoundValue;
  }

  @SuppressWarnings("unchecked")
  public static <I, O> O[] transform(
      I[] array,
      Class<? super O> clazz,
      ITransformer<I, O> transformer) {
    O[] transformed = (O[]) Array.newInstance(clazz, array.length);
    for (int i = 0; i < array.length; i++) {
      transformed[i] = transformer.transform(array[i]);
    }
    return transformed;
  }

  public static <I, O> O[] transform(I[] array, Class<O> clazz) {
    return transform(array, clazz, new CastingTransformer<I, O>());
  }
}