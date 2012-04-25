package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListOrderedSet<E> implements Set<E> {

  private final List<E> content = new ArrayList<E>();

  @Override
  public int size() {
    return content.size();
  }

  @Override
  public boolean isEmpty() {
    return content.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return content.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return content.iterator();
  }

  @Override
  public Object[] toArray() {
    return content.toArray();
  }

  @Override
  public <T> T[] toArray(T[] array) {
    return content.toArray(array);
  }

  @Override
  public boolean add(E e) {
    if (content.contains(e)) {
      return false;
    }
    return content.add(e);
  }

  @Override
  public boolean remove(Object object) {
    return content.remove(object);
  }

  @Override
  public boolean containsAll(Collection< ? > collection) {
    return content.containsAll(collection);
  }

  @Override
  public boolean addAll(Collection< ? extends E> collection) {
    boolean changed = false;
    for (E e : collection) {
      changed = add(e) || changed;
    }
    return changed;
  }

  @Override
  public boolean retainAll(Collection< ? > collection) {
    return content.retainAll(collection);
  }

  @Override
  public boolean removeAll(Collection< ? > collection) {
    return content.removeAll(collection);
  }

  @Override
  public void clear() {
    content.clear();
  }
}