package net.sf.anathema.initialization;

import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.resources.ExtensibleDataManager;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;
import net.sf.anathema.lib.resources.IResourceCollection;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceCollection;

import java.util.Collection;
import java.util.Set;

public class AnathemaInitializer {
	
  private final IAnathemaPreferences anathemaPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final AnathemaReflections reflections;
  private final ReflectionsInstantiater instantiater;

  public AnathemaInitializer(IAnathemaPreferences anathemaPreferences)
          throws InitializationException {
    this.reflections = new DefaultAnathemaReflections(anathemaPreferences);
    this.instantiater = new ReflectionsInstantiater(reflections);
    this.itemTypeCollection = new ItemTypeConfigurationCollection(instantiater);
    this.extensionCollection = new AnathemaExtensionCollection(instantiater);
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws InitializationException {
	AnathemaResources resources = initResources();
	ExtensibleDataManager dataSetManager = new ExtensibleDataManager();
    initializePlugins(reflections, dataSetManager);
    ProxySplashscreen.getInstance().displayVersion("v" + resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$//$NON-NLS-2$
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(new ResourceCollection(resources, dataSetManager));
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(anathemaModel, view, resources, reflections, itemTypeCollection.getItemTypes(), instantiater).initPresentation();
    return view;
  }

  private void initializePlugins(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws InitializationException {
    Collection<Startable> startablePlugins = instantiater.instantiateAll(Plugin.class);
    for (Startable startablePlugin : startablePlugins) {
      try {
        startablePlugin.doStart(reflections, registry);
      } catch (Exception e) {
        throw new InitializationException("Failed to start plugin.", e);
      }
    }
  }

  private IAnathemaModel initModel(IResourceCollection resources) throws InitializationException {
    ProxySplashscreen.getInstance().displayStatusMessage("Creating Model..."); //$NON-NLS-1$
    return new AnathemaModelInitializer(
            anathemaPreferences,
            itemTypeCollection.getItemTypes(),
            extensionCollection, instantiater).initializeModel(resources);
  }

  private IAnathemaView initView(IResources resources) {
    ProxySplashscreen.getInstance().displayStatusMessage("Building View..."); //$NON-NLS-1$
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, anathemaPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private AnathemaResources initResources() {
    AnathemaResources resources = new AnathemaResources();
    ProxySplashscreen.getInstance().displayStatusMessage("Loading Resources..."); //$NON-NLS-1$
    Set<IAnathemaResourceFile> resourcesInPaths = reflections.getResourcesMatching(".*\\.properties");
    for (IAnathemaResourceFile resource : resourcesInPaths) {
      resources.addResourceBundle(toBundleName(resource.getFileName()), resource);
    }
    return resources;
  }

  private String toBundleName(String input) {
      String resourceName = input.replace("/", ".").replace(".properties", "");
      boolean isInternationalizationFile = resourceName.matches(".*_..");
      if (isInternationalizationFile) {
        return resourceName.substring(0, resourceName.lastIndexOf("_"));
      } else {
        return resourceName;
      }
  }
}