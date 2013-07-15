package net.sf.anathema.lib.lang;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
    if (index != ArrayUtils.INDEX_NOT_FOUND) {
      return index;
    }
    throw new IllegalArgumentException("Value not contained in array: " + value);
  }

  public static <T> void reorder(T[] objects, int[] originalIndices, int[] newIndices) {
    if (originalIndices.length <= 1) {
      return;
    }
    Map<Integer, T> nodesByOriginalIndex = new HashMap<>();
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
    ArrayList<T> list = new ArrayList<>();
    Collections.addAll(list, array1);
    Collections.addAll(list, array2);
    return list.toArray(new ArrayFactory<>(clazz).createArray(list.size()));
  }

  public static <T> T getFirst(T[] array, Predicate<T> predicate) {
    for (T element : array) {
      if (predicate.apply(element)) {
        return element;
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <I, O> O[] transform(
          I[] array,
          Class<? super O> clazz,
          Function<I, O> transformer) {
    O[] transformed = (O[]) Array.newInstance(clazz, array.length);
    for (int i = 0; i < array.length; i++) {
      transformed[i] = transformer.apply(array[i]);
    }
    return transformed;
  }
}