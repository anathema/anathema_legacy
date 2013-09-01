package net.sf.anathema.initialization;

import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.environment.ApplicationEnvironment;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.ExceptionHandler;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.LocaleResources;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.framework.environment.dependencies.AggregatedResourceLoader;
import net.sf.anathema.initialization.reflections.CustomDataResourceLoader;
import net.sf.anathema.framework.environment.dependencies.DefaultAnathemaReflections;
import net.sf.anathema.framework.environment.dependencies.ReflectionObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.AnathemaException;

import java.util.Set;

public class EnvironmentFactory {

  private ExceptionHandler exceptionHandler;

  public EnvironmentFactory(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  public Environment create() {
    DefaultAnathemaReflections reflections = new DefaultAnathemaReflections();
    ResourceLoader loader = createResourceLoaderForInternalAndCustomResources(exceptionHandler, reflections);
    ObjectFactory objectFactory = new ReflectionObjectFactory(reflections);
    LocaleResources resources = initResources(loader);
    return new ApplicationEnvironment(resources, exceptionHandler, loader, objectFactory);
  }

  private LocaleResources initResources(ResourceLoader loader) {
    LocaleResources resources = new LocaleResources();
    Set<ResourceFile> resourcesInPaths = loader.getResourcesMatching(".*\\.properties");
    for (ResourceFile resource : resourcesInPaths) {
      resources.addResourceBundle(resource);
    }
    return resources;
  }

  private ResourceLoader createResourceLoaderForInternalAndCustomResources(ExceptionHandler exceptionHandler, DefaultAnathemaReflections reflections) {
    try {
      RepositoryLocationResolver resolver = new RepositoryLocationResolver(new InitializationPreferences());
      CustomDataResourceLoader customLoader = new CustomDataResourceLoader(resolver);
      return new AggregatedResourceLoader(reflections, customLoader);
    } catch (AnathemaException e) {
      exceptionHandler.handle(e);
      return new AggregatedResourceLoader(reflections);
    }
  }
}
