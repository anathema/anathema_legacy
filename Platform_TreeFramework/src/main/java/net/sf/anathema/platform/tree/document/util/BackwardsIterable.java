package net.sf.anathema.platform.tree.document.util;

import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return currentIndex > 0;
      }

      @Override
      public T next() {
        currentIndex--;
        return list.get(currentIndex);
      }

      @Override
      public void remove() {
        throw new NotYetImplementedException();
      }
    };
  }
}