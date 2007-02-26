package net.sf.anathema.initialization;

import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;
import net.sf.anathema.lib.resources.IResources;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class AnathemaInitializer {

  private static final String EXTENSION_POINT_RESOURCES = "AnathemaResources"; //$NON-NLS-1$
  private static final String PARAM_BUNDLE = "bundle"; //$NON-NLS-1$
  private final IAnathemaPreferences anathemaPreferences;
  private final AnathemaPluginManager pluginManager;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;

  public AnathemaInitializer(PluginManager manager, IAnathemaPreferences anathemaPreferences)
      throws InitializationException {
    this.pluginManager = new AnathemaPluginManager(manager);
    pluginManager.activatePlugins();
    this.itemTypeCollection = new ItemTypeConfigurationCollection(pluginManager, AnathemaEnvironment.isDevelopment());
    this.extensionCollection = new AnathemaExtensionCollection(pluginManager);
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws InitializationException {
    IResources resources = initResources();
    ProxySplashscreen.getInstance().displayVersion("v" + resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$//$NON-NLS-2$
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(resources);
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(pluginManager, anathemaModel, view, resources, itemTypeCollection.getItemTypes()).initPresentation();
    return view;

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
    ProxySplashscreen.getInstance().displayStatusMessage("Loading Resources..."); //$NON-NLS-1$
    AnathemaResources resources = new AnathemaResources();
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_RESOURCES)) {
      for (Parameter param : PluginUtilities.getParameters(extension, PARAM_BUNDLE)) {
        resources.addResourceBundle(param.valueAsString(), pluginManager.getClassLoader(extension));
      }
    }
    return resources;
  }
}