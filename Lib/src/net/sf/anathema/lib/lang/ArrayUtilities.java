package net.sf.anathema.lib.lang;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import net.sf.anathema.lib.util.CastingTransformer;
import net.sf.anathema.lib.util.IPredicate;
import net.sf.anathema.lib.util.ITransformer;
import net.sf.anathema.lib.util.ObjectUtilities;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayUtilities {

  public static int[] createIndexArray(int length) {
    int[] indexArray = new int[length];
    for (int index = 0; index < length; index++) {
      indexArray[index] = index;
    }
    return indexArray;
  }

  public static Integer[] createIntegerArray(int maximalValue) {
    Integer[] ranks = new Integer[Math.abs(maximalValue) + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = Integer.signum(maximalValue) * index;
    }
    return ranks;
  }

  public static Integer[] createIntegerArray(int minimalValue, int maximalValue) {
    Preconditions.checkState(minimalValue < maximalValue, "MinimalValue must be lower than mximalValue"); //$NON-NLS-1$
    Integer[] ranks = new Integer[maximalValue - minimalValue + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = minimalValue + index;
    }
    return ranks;
  }

  public static <T> T find(Predicate<T> predicate, T[] inputArray) {
    for (T input : inputArray) {
      if (predicate.apply(input)) {
        return input;
      }
    }
    return null;
  }

  public static <R> int indexOf(R[] array, R value) {
    int index = ArrayUtils.indexOf(array, value);
    if (index != ArrayUtils.INDEX_NOT_FOUND){
      return index;
    }
    throw new IllegalArgumentException("Value not contained in array: " + value); //$NON-NLS-1$
  }

  public static int max(int[] array) {
    int[] arrayCopy = new int[array.length];
    System.arraycopy(array, 0, arrayCopy, 0, array.length);
    Arrays.sort(arrayCopy);
    return arrayCopy[arrayCopy.length - 1];
  }

  public static void moveObject(Object[] array, int originalIndex, int targetIndex) {
    Object object = array[originalIndex];
    if (originalIndex < targetIndex) {
      System.arraycopy(array, originalIndex + 1, array, originalIndex, targetIndex - originalIndex);
      array[targetIndex] = object;
    }
    else {
      System.arraycopy(array, targetIndex, array, targetIndex + 1, originalIndex - targetIndex);
      array[targetIndex] = object;
    }
  }

  public static <T> void reorder(T[] objects, int[] originalIndices, int[] newIndices) {
    if (originalIndices.length <= 1) {
      return;
    }
    Map<Integer, T> nodesByOriginalIndex = new HashMap<Integer, T>();
    for (int element : originalIndices) {
      nodesByOriginalIndex.put(element, objects[element]);
    }
    for (int indexIndex = 0; indexIndex < originalIndices.length; indexIndex++) {
      int originalIndex = originalIndices[indexIndex];
      int newIndex = newIndices[indexIndex];
      objects[newIndex] = nodesByOriginalIndex.get(originalIndex);
    }
  }

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