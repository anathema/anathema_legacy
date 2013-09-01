package net.sf.anathema.framework.environment;

import de.idos.updates.configuration.PropertiesLoader;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.initialization.InitializationException;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class ApplicationEnvironment implements Environment {
  private final Resources resources;
  private final ExceptionHandler handler;
  private final ResourceLoader loader;
  private final ObjectFactory objectFactory;

  public ApplicationEnvironment(Resources resources, ExceptionHandler handler, ResourceLoader loader, ObjectFactory objectFactory) {
    this.resources = resources;
    this.handler = handler;
    this.loader = loader;
    this.objectFactory = objectFactory;
  }

  @Override
  public void handle(Throwable exception) {
    handler.handle(exception);
  }

  @Override
  public void handle(Throwable throwable, String message) {
    handler.handle(throwable, message);
  }

  @Override
  public boolean supportsKey(String key) {
    return resources.supportsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    return resources.getString(key, arguments);
  }

  @Override
  public String getPreference(String key) {
    return new PropertiesLoader("preferences.properties").load().getProperty(key);
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return objectFactory.instantiateOrdered(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation, Object... parameter) throws InitializationException {
    return objectFactory.instantiateAll(annotation, parameter);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return loader.getResourcesMatching(namePattern);
  }
}