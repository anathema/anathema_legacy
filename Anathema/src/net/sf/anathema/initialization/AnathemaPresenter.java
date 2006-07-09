package net.sf.anathema.initialization;

import java.util.Collection;

import net.sf.anathema.development.DevelopmentEnvironmentPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.modules.PresentationExtensionPointFiller;
import net.sf.anathema.initialization.modules.PresentationInitializer;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class AnathemaPresenter {

  private static final String PARAM_CLASS = "class"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_TOOLBAR = "Toolbar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_MENUBAR = "Menubar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_REPORT_FACTORIES = "ReportFactories"; //$NON-NLS-1$
  private final IAnathemaModel model;
  private final IAnathemaView view;
  private final IAnathemaResources resources;
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations;
  private final IAnathemaPluginManager pluginManager;

  public AnathemaPresenter(
      IAnathemaPluginManager pluginManager,
      IAnathemaModel model,
      IAnathemaView view,
      IAnathemaResources resources,
      Collection<AbstractItemTypeConfiguration> itemTypeConfigurations) {
    this.pluginManager = pluginManager;
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.itemTypeConfigurations = itemTypeConfigurations;
  }

  public void initPresentation(IModuleCollection moduleCollection) throws InitializationException {
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.fillPresentationExtensionPoints(model.getExtensionPointRegistry(), resources, model, view);
    }
    initializePreferences(moduleCollection);
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerViewFactory(model, resources);
    }
    new PresentationInitializer(moduleCollection, resources, model, view).initialize();
    initializeMenus();
    initializeTools();
    initializeReports();
    if (AnathemaEnvironment.isDevelopment()) {
      new DevelopmentEnvironmentPresenter(model, view, resources).initPresentation();
    }
  }

  private void initializePreferences(IModuleCollection moduleCollection) {
    new PresentationExtensionPointFiller(moduleCollection, model, view, resources).initialize();
  }

  private void initializeReports() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(
        IPluginConstants.PLUGIN_CORE,
        EXTENSION_POINT_REPORT_FACTORIES)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IReportFactory reportFactory = (IReportFactory) PluginUtilities.instantiate(parameter);
        model.getReportRegistry().addReports(reportFactory.createReport(resources, model.getExtensionPointRegistry()));
      }
    }
  }


  private void initializeMenus() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_MENUBAR)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IAnathemaMenu tool = (IAnathemaMenu) PluginUtilities.instantiate(parameter);
        tool.add(resources, model, view.getMenuBar());
      }
    }
  }

  private void initializeTools() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_TOOLBAR)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IAnathemaTool tool = (IAnathemaTool) PluginUtilities.instantiate(parameter);
        tool.add(resources, model, view.getToolbar());
      }
    }
  }
}