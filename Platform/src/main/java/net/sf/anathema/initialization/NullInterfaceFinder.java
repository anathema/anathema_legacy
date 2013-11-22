package net.sf.anathema.initialization;

import net.sf.anathema.framework.environment.dependencies.InterfaceFinder;

import java.util.Collections;
import java.util.Set;

public class NullInterfaceFinder implements InterfaceFinder {
  @Override
  public Set<Class<?>> findAll(Class interfaceClass) {
    return Collections.emptySet();
  }
}
