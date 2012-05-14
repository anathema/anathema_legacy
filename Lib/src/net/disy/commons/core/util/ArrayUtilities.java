/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.util;

import net.disy.commons.core.predicate.IPredicate;

import java.lang.reflect.Array;

public class ArrayUtilities {

  @SuppressWarnings("unchecked")
  public static <T> T[] concat(final Class<T> clazz, final T[] array1, final T... array2) {
    if (array2 == null) {
      return array1;
    }
    if (array1 == null) {
      return array2;
    }
    final T[] mergedArray = (T[]) Array.newInstance(clazz, array1.length + array2.length);
    System.arraycopy(array1, 0, mergedArray, 0, array1.length);
    System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
    return mergedArray;
  }

  public static <T> boolean containsValue(final T[] array, final T value) {
    return contains(array, new IPredicate<T>() {
      @Override
      public boolean evaluate(final T actualValue) {
        return ObjectUtilities.equals(value, actualValue);
      }
    });
  }

  public static <T> boolean contains(final T[] array, final IPredicate<T> predicate) {
    for (final T element : array) {
      if (predicate.evaluate(element)) {
        return true;
      }
    }
    return false;
  }

  public static <T> T getFirst(final T[] array, final IPredicate<T> predicate) {
    final T notFoundValue = null;
    return getFirst(array, predicate, notFoundValue);
  }

  public static <T> T getFirst(final T[] array, final IPredicate<T> predicate, final T notFoundValue) {
    for (final T element : array) {
      if (predicate.evaluate(element)) {
        return element;
      }
    }
    return notFoundValue;
  }

  @SuppressWarnings("unchecked")
  public static <I, O> O[] transform(
      final I[] array,
      final Class<? super O> clazz,
      final ITransformer<I, O> transformer) {
    final O[] transformed = (O[]) Array.newInstance(clazz, array.length);
    for (int i = 0; i < array.length; i++) {
      transformed[i] = transformer.transform(array[i]);
    }
    return transformed;
  }

  public static <I, O> O[] transform(final I[] array, final Class<O> clazz) {
    return transform(array, clazz, new CastingTransformer<I, O>());
  }
}