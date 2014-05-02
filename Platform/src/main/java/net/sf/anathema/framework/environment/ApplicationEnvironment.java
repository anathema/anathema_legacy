package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

public class ApplicationEnvironment implements Environment {
  private final Resources resources;
  private final ExceptionHandler handler;
  private final ResourceLoader loader;
  private final ObjectFactory objectFactory;
  private final Preferences preferences;
  private SingleFileChooser fileChooser;

  public ApplicationEnvironment(Resources resources, ExceptionHandler handler, ResourceLoader loader,
                                ObjectFactory objectFactory, Preferences preferences, SingleFileChooser fileChooser) {
    this.resources = resources;
    this.handler = handler;
    this.loader = loader;
    this.objectFactory = objectFactory;
    this.preferences = preferences;
    this.fileChooser = fileChooser;
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
    return preferences.getPreference(key);
  }

  @Override
  public <T> Collection<T> instantiateOrdered(Class<? extends Annotation> annotation,
                                              Object... parameter) throws InitializationException {
    return objectFactory.instantiateOrdered(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAll(Class<? extends Annotation> annotation,
                                          Object... parameter) throws InitializationException {
    return objectFactory.instantiateAll(annotation, parameter);
  }

  @Override
  public <T> Collection<T> instantiateAllImplementers(Class<T> interfaceClass, Object... parameter) {
    return objectFactory.instantiateAllImplementers(interfaceClass, parameter);
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(String namePattern) {
    return loader.getResourcesMatching(namePattern);
  }

  @Override
  public Path selectSaveFile(FileChooserConfiguration configuration) {
    return fileChooser.selectSaveFile(configuration);
  }

  @Override
  public Path selectLoadFile(Extension extension) {
    return fileChooser.selectLoadFile(extension);
  }
}