package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.presenter.view.ContentFactory;

import java.util.HashMap;
import java.util.Map;

public class SpecialContentMap {
  private final Map<Class, ContentFactory> content = new HashMap<>();

  public void put(Class contentClass, ContentFactory factory) {
    content.put(contentClass, factory);
  }

  public <T> T create(Class<T> clazz, Object[] parameters) {
    return content.get(clazz).create(parameters);
  }
}
