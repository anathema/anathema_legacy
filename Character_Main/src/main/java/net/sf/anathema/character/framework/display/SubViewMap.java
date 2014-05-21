package net.sf.anathema.character.framework.display;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.util.ReflectionFactoryMap;

public class SubViewMap implements SubViewRegistry {

  private final ReflectionFactoryMap<SubViewFactory> factories;

  public SubViewMap(ObjectFactory objectFactory) {
    factories = new ReflectionFactoryMap<>(objectFactory, SubViewFactory.class);
  }

  public <T> T get(Class<T> viewClass) {
    return factories.get(viewClass).create();
  }
}