package net.sf.anathema.charmtree.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.sf.anathema.lib.exception.NotYetImplementedException;

public class BackwardsIterable<T> implements Iterable<T> {

  private final List<T> list;
  private int currentIndex;

  public BackwardsIterable(List<T> list) {
    this.list = list;
    currentIndex = list.size();
  }

  public BackwardsIterable(T[] array) {
    this(Arrays.asList(array));
  }

  public Iterator<T> iterator() {
    return new Iterator<T>() {
      public boolean hasNext() {
        return currentIndex > 0;
      }

      public T next() {
        currentIndex--;
        return list.get(currentIndex);
      }

      public void remove() {
        throw new NotYetImplementedException();
      }
    };
  }
}