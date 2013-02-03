package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.initialization.reflections.AggregatedResourceLoader;
import net.sf.anathema.initialization.reflections.CustomDataResourceLoader;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.CentralExceptionHandling;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.Collection;
import java.util.Set;

public abstract class Initializer {

  private final IInitializationPreferences initializationPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final DefaultAnathemaReflections reflections;
  private final ReflectionsInstantiater instantiater;

  public Initializer(IInitializationPreferences initializationPreferences) throws InitializationException {
    this.reflections = new DefaultAnathemaReflections();
    this.instantiater = new ReflectionsInstantiater(reflections);
    this.itemTypeCollection = new ItemTypeConfigurationCollection(instantiater);
    this.extensionCollection = new AnathemaExtensionCollection(instantiater);
    this.initializationPreferences = initializationPreferences;
  }

  protected InitializedModelAndView initializeModelViewAndPresentation() throws InitializationException {
    ResourceLoader loader = createResourceLoaderForInternalAndCustomResources();
    AnathemaResources resources = initResources(loader);
    showVersion(resources);
    configureExceptionHandling(resources);
    IAnathemaModel anathemaModel = initModel(resources, loader);
    MainView view = initView(resources);
    initPresentation(resources, anathemaModel, view);
    return new InitializedModelAndView(view, anathemaModel);
  }

  private void initPresentation(AnathemaResources resources, IAnathemaModel anathemaModel, MainView view) {
    Collection<IItemTypeConfiguration> itemTypes = itemTypeCollection.getItemTypes();
    AnathemaPresenter presenter = new AnathemaPresenter(anathemaModel, view, resources, itemTypes, instantiater);
    presenter.initPresentation();
  }

  private void configureExceptionHandling(AnathemaResources resources) {
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
  }

  private IAnathemaModel initModel(IResources resources, ResourceLoader loader) throws InitializationException {
    displayMessage("Creating Model...");
    Collection<IItemTypeConfiguration> itemTypes = itemTypeCollection.getItemTypes();
    AnathemaModelInitializer modelInitializer = new AnathemaModelInitializer(initializationPreferences, itemTypes,
            extensionCollection);
    return modelInitializer.initializeModel(resources, reflections, loader);
  }

  private AnathemaResources initResources(ResourceLoader loader) {
    displayMessage("Loading Resources...");
    AnathemaResources resources = new AnathemaResources();
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

  protected abstract void showVersion(IResources resources);

  protected abstract void displayMessage(String message);

  protected abstract MainView initView(IResources resources);
}