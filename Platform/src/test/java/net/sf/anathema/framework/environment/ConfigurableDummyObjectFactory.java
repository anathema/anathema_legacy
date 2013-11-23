package net.sf.anathema.framework.environment;

import com.google.common.collect.HashMultimap;
import net.sf.anathema.initialization.InitializationException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ConfigurableDummyObjectFactory implements ObjectFactory {
  private final HashMultimap<Class, Object> objectsForInterfaces = HashMultimap.create();

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    return Collections.emptyList();
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    return Collections.emptyList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    Set<T> objects = (Set<T>) objectsForInterfaces.get(interfaceClass);
    return new ArrayList<>(objects);
  }

  public void add(Class<?> interfaceClass, Object instance) {
    objectsForInterfaces.put(interfaceClass, instance);
  }
}
