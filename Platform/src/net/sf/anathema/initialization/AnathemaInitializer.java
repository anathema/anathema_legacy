package net.sf.anathema.initialization;

import com.google.common.base.Function;
import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Collections2.transform;

public class AnathemaInitializer {

  private final IAnathemaPreferences anathemaPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final AnathemaReflections reflections;

  public AnathemaInitializer(IAnathemaPreferences anathemaPreferences)
          throws InitializationException {
    this.reflections = new DefaultAnathemaReflections();
    this.itemTypeCollection = new ItemTypeConfigurationCollection(new ReflectionsInstantiater(reflections));
    this.extensionCollection = new AnathemaExtensionCollection(new ReflectionsInstantiater(reflections));
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws InitializationException {
    initializePlugins(reflections);
    IResources resources = initResources();
    ProxySplashscreen.getInstance().displayVersion("v" + resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$//$NON-NLS-2$
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(resources);
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(anathemaModel, view, resources, itemTypeCollection.getItemTypes(), new ReflectionsInstantiater(reflections)).initPresentation();
    return view;

  }

  private void initializePlugins(AnathemaReflections reflections) throws InitializationException {
    Collection<Startable> startablePlugins = new ReflectionsInstantiater(reflections).instantiateAll(Plugin.class);
    for (Startable startablePlugin : startablePlugins) {
      try {
        startablePlugin.doStart(reflections);
      } catch (Exception e) {
        throw new InitializationException("Failed to start plugin.", e);
      }
    }
  }

  private IAnathemaModel initModel(IResources resources) throws InitializationException {
    ProxySplashscreen.getInstance().displayStatusMessage("Creating Model..."); //$NON-NLS-1$
    return new AnathemaModelInitializer(
            anathemaPreferences,
            itemTypeCollection.getItemTypes(),
            extensionCollection.getExtensionsById()).initializeModel(resources);
  }

  private IAnathemaView initView(IResources resources) {
    ProxySplashscreen.getInstance().displayStatusMessage("Building View..."); //$NON-NLS-1$
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, anathemaPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private IResources initResources() {
    AnathemaResources resources = new AnathemaResources();
    ProxySplashscreen.getInstance().displayStatusMessage("Loading Resources..."); //$NON-NLS-1$
    Set<String> resourcesInPaths = reflections.getResourcesMatching(".*\\.properties");
    Collection<String> bundlesInPackages = transform(resourcesInPaths, new ToBundleName());
    for (String resource : new HashSet<String>(bundlesInPackages)) {
      resources.addResourceBundle(resource, getClass().getClassLoader());
    }
    return resources;
  }

  private static class ToBundleName implements Function<String, String> {
    @Override
    public String apply(String input) {
      String resourceName = input.replace("/", ".").replace(".properties", "");
      boolean isInternationalizationFile = resourceName.matches(".*_..");
      if (isInternationalizationFile) {
        return resourceName.substring(0, resourceName.lastIndexOf("_"));
      } else {
        return resourceName;
      }
    }
  }
}