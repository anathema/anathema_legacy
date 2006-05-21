package net.sf.anathema.lib.lang;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.collection.Predicate;

public class ArrayUtilities {

  public static int max(int[] array) {
    int[] arrayCopy = new int[array.length];
    System.arraycopy(array, 0, arrayCopy, 0, array.length);
    Arrays.sort(arrayCopy);
    return arrayCopy[arrayCopy.length - 1];
  }

  public static boolean equals(Object[] first, Object[] second) {
    if (first.length != second.length) {
      return false;
    }
    for (int index = 0; index < first.length; index++) {
      if (!AnathemaStringUtilities.bothNullOrEquals(first[index], second[index])) {
        return false;
      }
    }
    return true;
  }

  public static boolean contains(Object[] array, Object value) {
    if (value == null) {
      return false;
    }
    for (Object object : array) {
      if (value.equals(object)) {
        return true;
      }
    }
    return false;
  }

  public static void copyAll(Object[] sourceArray, Object[] targetArray) {
    System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
  }

  @SuppressWarnings("unchecked")
  public static <R, I> R[] transform(ITransformer<I, R> transformer, I[] inputArray, Class<R> resultClass) {
    R[] resultArray = (R[]) Array.newInstance(resultClass, inputArray.length);
    for (int index = 0; index < inputArray.length; index++) {
      resultArray[index] = transformer.transform(inputArray[index]);
    }
    return resultArray;
  }

  public static <T> T find(Predicate<T> predicate, T[] inputArray) {
    for (T input : inputArray) {
      if (predicate.evaluate(input)) {
        return input;
      }
    }
    return null;
  }

  public static <R> int indexOf(R[] array, R value) {
    for (int index = 0; index < array.length; index++) {
      if (array[index].equals(value)) {
        return index;
      }
    }
    throw new IllegalArgumentException("Value not contained in array: " + value); //$NON-NLS-1$
  }

  public static <T> void reorder(T[] objects, int[] originalIndices, int[] newIndices) {
    if (originalIndices.length <= 1) {
      return;
    }
    Map<Integer, T> nodesByOriginalIndex = new HashMap<Integer, T>();
    for (int indexIndex = 0; indexIndex < originalIndices.length; indexIndex++) {
      nodesByOriginalIndex.put(originalIndices[indexIndex], objects[originalIndices[indexIndex]]);
    }
    for (int indexIndex = 0; indexIndex < originalIndices.length; indexIndex++) {
      int originalIndex = originalIndices[indexIndex];
      int newIndex = newIndices[indexIndex];
      objects[newIndex] = nodesByOriginalIndex.get(originalIndex);
    }
  }

  public static Integer[] createIntegerArray(int maximalValue) {
    Integer[] ranks = new Integer[Math.abs(maximalValue) + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = Integer.signum(maximalValue) * index;
    }
    return ranks;
  }

  public static Integer[] createIntegerArray(int minimalValue, int maximalValue) {
    Ensure.ensureArgumentTrue("MinimalValue must be lower than mximalValue", minimalValue < maximalValue); //$NON-NLS-1$
    Integer[] ranks = new Integer[maximalValue - minimalValue + 1];
    for (int index = 0; index < ranks.length; index++) {
      ranks[index] = minimalValue + index;
    }
    return ranks;
  }

  public static int[] createIndexArray(int length) {
    int[] indexArray = new int[length];
    for (int index = 0; index < length; index++) {
      indexArray[index] = index;
    }
    return indexArray;
  }

  private static int[] createInvertedIndexArray(int length) {
    int[] indexArray = new int[length];
    for (int index = 0; index < length; index++) {
      indexArray[index] = length - 1 - index;
    }
    return indexArray;
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

  public static void invert(Object[] array) {
    int[] indices = createIndexArray(array.length);
    int[] invertedIndices = createInvertedIndexArray(array.length);
    reorder(array, indices, invertedIndices);
  }

  public static float[] toPrimitive(Float[] objectArray) {
    float[] primitiveArray = new float[objectArray.length];
    for (int index = 0; index < primitiveArray.length; index++) {
      primitiveArray[index] = objectArray[index];
    }
    return primitiveArray;
  }
}