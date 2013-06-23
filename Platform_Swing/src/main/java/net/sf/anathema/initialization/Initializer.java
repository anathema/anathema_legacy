package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.module.ItemTypeConfiguration;
import net.sf.anathema.framework.resources.LocaleResources;
import net.sf.anathema.framework.swing.SwingDialogExceptionHandler;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.initialization.reflections.AggregatedResourceLoader;
import net.sf.anathema.initialization.reflections.CustomDataResourceLoader;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.CentralExceptionHandling;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collection;
import java.util.Set;

public abstract class Initializer {

  private final IInitializationPreferences initializationPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final DefaultAnathemaReflections reflections;
  private final ObjectFactory objectFactory;

  public Initializer(IInitializationPreferences initializationPreferences) throws InitializationException {
    this.reflections = new DefaultAnathemaReflections();
    this.objectFactory = new ReflectionObjectFactory(reflections);
    this.itemTypeCollection = new ItemTypeConfigurationCollection(objectFactory);
    this.extensionCollection = new AnathemaExtensionCollection(objectFactory);
    this.initializationPreferences = initializationPreferences;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    ResourceLoader loader = createResourceLoaderForInternalAndCustomResources();
    LocaleResources resources = initResources(loader);
    showVersion(resources);
    configureExceptionHandling(resources);
    IApplicationModel anathemaModel = initModel(resources, loader);
    ApplicationFrameView view = initView(resources, anathemaModel, objectFactory);
    initPresentation(resources, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  protected void initPresentation(LocaleResources resources, IApplicationModel anathemaModel, ApplicationView view) {
    Collection<ItemTypeConfiguration> itemTypes = itemTypeCollection.getItemTypes();
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, resources, itemTypes, objectFactory);
    presenter.initPresentation();
  }

  private void configureExceptionHandling(LocaleResources resources) {
    CentralExceptionHandling.setHandler(new SwingDialogExceptionHandler(resources));
  }

  private IApplicationModel initModel(Resources resources, ResourceLoader loader) throws InitializationException {
    displayMessage("Creating Model...");
    Collection<net.sf.anathema.framework.module.ItemTypeConfiguration> itemTypes = itemTypeCollection.getItemTypes();
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer(initializationPreferences, itemTypes, extensionCollection);
    return modelInitializer.initializeModel(resources, reflections, loader);
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
    RepositoryLocationResolver resolver = new RepositoryLocationResolver(initializationPreferences);
    CustomDataResourceLoader customLoader = new CustomDataResourceLoader(resolver);
    return new AggregatedResourceLoader(reflections, customLoader);
  }

  protected IInitializationPreferences getPreferences() {
    return initializationPreferences;
  }

  protected abstract void showVersion(Resources resources);

  protected abstract void displayMessage(String message);

  protected abstract ApplicationFrameView initView(Resources resources, IApplicationModel anathemaModel, ObjectFactory objectFactory);
}