package net.sf.anathema.framework.environment.dependencies;

import java.util.Set;

public interface InterfaceFinder {
  public <T> Set<Class<? extends T>> findAll(Class<T> interfaceClass);
}
