package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListOrderedSet<E> implements Set<E> {

  private final List<E> content = new ArrayList<E>();

  public int size() {
    return content.size();
  }

  public boolean isEmpty() {
    return content.isEmpty();
  }

  public boolean contains(Object o) {
    return content.contains(o);
  }

  public Iterator<E> iterator() {
    return content.iterator();
  }

  public Object[] toArray() {
    return content.toArray();
  }

  public <T> T[] toArray(T[] array) {
    return content.toArray(array);
  }

  public boolean add(E e) {
    if (content.contains(e)) {
      return false;
    }
    return content.add(e);
  }

  public boolean remove(Object object) {
    return content.remove(object);
  }

  public boolean containsAll(Collection< ? > collection) {
    return content.containsAll(collection);
  }

  public boolean addAll(Collection< ? extends E> collection) {
    boolean changed = false;
    for (E e : collection) {
      changed = add(e) || changed;
    }
    return changed;
  }

  public boolean retainAll(Collection< ? > collection) {
    return content.retainAll(collection);
  }

  public boolean removeAll(Collection< ? > collection) {
    return content.removeAll(collection);
  }

  public void clear() {
    content.clear();
  }
}