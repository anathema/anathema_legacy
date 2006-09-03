package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;

import com.db4o.ObjectContainer;
import com.db4o.types.Db4oMap;

public class Db4OCollectionFactory implements ICollectionFactory {

  private final ObjectContainer container;

  public Db4OCollectionFactory(ObjectContainer container) {
    this.container = container;
  }

  @SuppressWarnings("unchecked")
  public <K, V> Map<K, V> createHashMap() {
    Db4oMap map = container.ext().collections().newHashMap(16);
    map.activationDepth(3);
    return map;
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> createList() {
    return container.ext().collections().newLinkedList();
  }
}