package net.sf.anathema;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.dependencies.DefaultAnathemaReflections;
import net.sf.anathema.framework.environment.dependencies.ReflectionObjectFactory;
import net.sf.anathema.framework.environment.exception.ConsoleExceptionHandler;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.NullInterfaceFinder;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class DummyEnvironment implements Environment {
  
  private final DefaultAnathemaReflections finder = new DefaultAnathemaReflections();
  private final ObjectFactory factory = new ReflectionObjectFactory(finder, new NullInterfaceFinder());
  
  @Override
  public void handle(Throwable exception) {
    new ConsoleExceptionHandler().handle(exception);
  }

  @Override
  public void handle(Throwable exception, String message) {
    new ConsoleExceptionHandler().handle(exception, message);
  }

  @Override
  public String getPreference(String key) {
    return null;
  }

  @Override
  public boolean supportsKey(String key) {
    return false;
  }

  @Override
  public String getString(String key, Object... arguments) {
    return key;
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return factory.instantiateOrdered(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return factory.instantiateAll(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    return factory.instantiateAllImplementers(interfaceClass, parameter);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return finder.getResourcesMatching(namePattern);
  }
}