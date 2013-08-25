package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.environment.ApplicationEnvironment;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.resources.LocaleResources;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.initialization.reflections.AggregatedResourceLoader;
import net.sf.anathema.initialization.reflections.CustomDataResourceLoader;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.ExceptionHandler;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.Resources;

import java.util.Set;

public abstract class Initializer {

  private final RepositoryPreference initializationPreferences;
  private final AnathemaExtensionCollection extensionCollection;
  private final DefaultAnathemaReflections reflections;
  private final ObjectFactory objectFactory;
  private final ExceptionHandler exceptionHandler;

  public Initializer(RepositoryPreference initializationPreferences, ExceptionHandler exceptionHandler) throws InitializationException {
    this.exceptionHandler = exceptionHandler;
    this.reflections = new DefaultAnathemaReflections();
    this.objectFactory = new ReflectionObjectFactory(reflections);
    this.extensionCollection = new AnathemaExtensionCollection(objectFactory);
    this.initializationPreferences = initializationPreferences;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    ResourceLoader loader = createResourceLoaderForInternalAndCustomResources();
    LocaleResources resources = initResources(loader);
    configureExceptionHandling(resources);
    showVersion(resources);
    IApplicationModel anathemaModel = initModel(resources, loader);
    Environment environment = new ApplicationEnvironment(resources, exceptionHandler);
    ApplicationFrameView view = initView(environment, anathemaModel, objectFactory);
    initPresentation(resources, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  protected void configureExceptionHandling(Resources resources) {
    //nothing to do
  }

  protected void initPresentation(LocaleResources resources, IApplicationModel anathemaModel, ApplicationView view) {
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, resources, objectFactory);
    presenter.initPresentation();
  }

  private IApplicationModel initModel(Resources resources, ResourceLoader loader) throws InitializationException {
    displayMessage("Creating Model...");
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer(initializationPreferences, extensionCollection);
    return modelInitializer.initializeModel(resources, objectFactory, loader);
  }

  private LocaleResources initResources(ResourceLoader loader) {
    displayMessage("Loading Resources...");
    LocaleResources resources = new LocaleResources();
    Set<ResourceFile> resourcesInPaths = loader.getResourcesMatching(".*\\.properties");
    for (ResourceFile resource : resourcesInPaths) {
      resources.addResourceBundle(resource);
    }
    return resources;
  }

  private ResourceLoader createResourceLoaderForInternalAndCustomResources() {
    try {
      RepositoryLocationResolver resolver = new RepositoryLocationResolver(initializationPreferences);
      CustomDataResourceLoader customLoader = new CustomDataResourceLoader(resolver);
      return new AggregatedResourceLoader(reflections, customLoader);
    } catch (AnathemaException e) {
      exceptionHandler.handle(e);
      return new AggregatedResourceLoader(reflections);
    }
  }

  protected abstract void showVersion(Resources resources);

  protected abstract void displayMessage(String message);

  protected abstract ApplicationFrameView initView(Environment environment, IApplicationModel anathemaModel, ObjectFactory objectFactory);
}