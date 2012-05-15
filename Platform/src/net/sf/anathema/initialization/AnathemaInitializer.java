package net.sf.anathema.initialization;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.reflections.AggregatedResourceLoader;
import net.sf.anathema.initialization.reflections.CustomDataResourceLoader;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.exception.CentralExceptionHandling;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.Set;

public class AnathemaInitializer {

  private final IInitializationPreferences initializationPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final DefaultAnathemaReflections reflections;
  private final ReflectionsInstantiater instantiater;

  public AnathemaInitializer(IInitializationPreferences initializationPreferences) throws InitializationException {
    this.reflections = new DefaultAnathemaReflections();
    this.instantiater = new ReflectionsInstantiater(reflections);
    this.itemTypeCollection = new ItemTypeConfigurationCollection(instantiater);
    this.extensionCollection = new AnathemaExtensionCollection(instantiater);
    this.initializationPreferences = initializationPreferences;
  }

  public IAnathemaView initialize() throws InitializationException {
    ResourceLoader loader = createResourceLoaderForInternalAndCustomResources();
    AnathemaResources resources = initResources(loader);
    ProxySplashscreen.getInstance().displayVersion(
            "v" + resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$//$NON-NLS-2$
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(resources, loader);
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(anathemaModel, view, resources, itemTypeCollection.getItemTypes(),
            instantiater).initPresentation();
    return view;
  }

  private IAnathemaModel initModel(IResources resources, ResourceLoader loader) throws InitializationException {
    ProxySplashscreen.getInstance().displayStatusMessage("Creating Model..."); //$NON-NLS-1$
    return new AnathemaModelInitializer(initializationPreferences, itemTypeCollection.getItemTypes(),
            extensionCollection).initializeModel(resources, reflections, loader);
  }

  private IAnathemaView initView(IResources resources) {
    ProxySplashscreen.getInstance().displayStatusMessage("Building View..."); //$NON-NLS-1$
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources,
            initializationPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private AnathemaResources initResources(ResourceLoader loader) {
    AnathemaResources resources = new AnathemaResources();
    ProxySplashscreen.getInstance().displayStatusMessage("Loading Resources..."); //$NON-NLS-1$
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
}