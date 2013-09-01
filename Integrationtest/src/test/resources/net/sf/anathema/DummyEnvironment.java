package net.sf.anathema;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.exception.ConsoleExceptionHandler;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.initialization.InitializationException;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class DummyEnvironment implements Environment {
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
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
