package net.sf.anathema.initialization;

import java.awt.AWTException;

import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;
import net.sf.anathema.lib.resources.IResources;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class AnathemaInitializer {

  private static final String EXTENSION_POINT_RESOURCES = "AnathemaResources"; //$NON-NLS-1$
  private static final String PARAM_BUNDLE = "bundle"; //$NON-NLS-1$
  private final IAnathemaPreferences anathemaPreferences;
  private final AnathemaPluginManager pluginManager;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;

  public AnathemaInitializer(IAnathemaPreferences anathemaPreferences) throws InitializationException {
    this.pluginManager = new AnathemaPluginManager();
    pluginManager.collectPlugins();
    pluginManager.activatePlugins();
    this.itemTypeCollection = new ItemTypeConfigurationCollection(pluginManager, AnathemaEnvironment.isDevelopment());
    this.extensionCollection = new AnathemaExtensionCollection(pluginManager);
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws Exception {
    IAnathemaResources resources = initResources();
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(resources);
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(pluginManager, anathemaModel, view, resources, itemTypeCollection.getItemTypes()).initPresentation();
    return view;
  }

  private IAnathemaModel initModel(IResources resources) throws RepositoryException {
    return new AnathemaModelInitializer(
        anathemaPreferences,
        itemTypeCollection.getItemTypes(),
        extensionCollection.getExtensionsById()).initializeModel(resources);
  }

  private IAnathemaView initView(IAnathemaResources resources) throws AWTException {
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, anathemaPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private IAnathemaResources initResources() {
    IAnathemaResources resources = new AnathemaResources();
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_RESOURCES)) {
      for (Parameter param : PluginUtilities.getParameters(extension, PARAM_BUNDLE)) {
        resources.addResourceBundle(param.valueAsString());
      }
    }
    return resources;
  }
}