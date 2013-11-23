package net.sf.anathema.character.main.view;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.util.ObjectFactoryMap;

public class SubViewMap implements SubViewRegistry {

  private final ObjectFactoryMap<SubViewFactory> factories;

  public SubViewMap(ObjectFactory objectFactory) {
    factories = new ObjectFactoryMap<>(objectFactory, SubViewFactory.class);
  }

  public <T> T get(Class<T> viewClass) {
    return factories.get(viewClass).create();
  }
}