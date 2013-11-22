package net.sf.anathema.framework.environment.dependencies;

import java.util.Set;

public interface InterfaceFinder {
  Set<Class<?>> findAll(Class interfaceClass);
}
