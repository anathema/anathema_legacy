package net.sf.anathema.initialization;

import java.util.Collection;

import net.sf.anathema.development.DevelopmentEnvironmentPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;
import net.sf.anathema.lib.resources.IResources;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class AnathemaPresenter {

  private static final String PARAM_CLASS = "class"; //$NON-NLS-1$
  private static final String PARAM_KEY = "preferencekey"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_BOOTJOBS = "Bootjobs"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_TOOLBAR = "Toolbar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_MENUBAR = "Menubar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_REPORT_FACTORIES = "ReportFactories"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_PREFERENCE_ELEMENTS = "PreferenceElements"; //$NON-NLS-1$
  private final IAnathemaModel model;
  private final IAnathemaView view;
  private final IResources resources;
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations;
  private final IAnathemaPluginManager pluginManager;

  public AnathemaPresenter(
      IAnathemaPluginManager pluginManager,
      IAnathemaModel model,
      IAnathemaView view,
      IResources resources,
      Collection<AbstractItemTypeConfiguration> itemTypeConfigurations) {
    this.pluginManager = pluginManager;
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.itemTypeConfigurations = itemTypeConfigurations;
  }

  public void initPresentation() throws InitializationException {
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.fillPresentationExtensionPoints(model.getExtensionPointRegistry(), resources, model, view);
    }
    initializePreferences();
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerViewFactory(model, resources);
    }
    for (AbstractItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerCreationWizardFactory(model, resources);
    }
    runBootJobs();
    initializeMenus();
    initializeTools();
    initializeReports();
    if (AnathemaEnvironment.isDevelopment()) {
      new DevelopmentEnvironmentPresenter(model, view, resources).initPresentation();
    }
  }

  private void runBootJobs() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_BOOTJOBS)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        ((IAnathemaBootJob) PluginUtilities.instantiate(parameter, pluginManager)).run(resources, model, view);
      }
    }
  }

  private void initializePreferences() throws InitializationException {
    PreferencesElementsExtensionPoint extensionPoint = (PreferencesElementsExtensionPoint) model.getExtensionPointRegistry()
        .get(PreferencesElementsExtensionPoint.ID);
    for (Extension extension : pluginManager.getExtension(
        IPluginConstants.PLUGIN_CORE,
        EXTENSION_POINT_PREFERENCE_ELEMENTS)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_TYPE)) {
        Parameter classParameter = parameter.getSubParameter(PARAM_CLASS);
        Parameter keyParameter = parameter.getSubParameter(PARAM_KEY);
        IPreferencesElement element = (IPreferencesElement) PluginUtilities.instantiate(classParameter, pluginManager);
        extensionPoint.register(keyParameter.valueAsString(), element);
      }
    }
  }

  private void initializeReports() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(
        IPluginConstants.PLUGIN_CORE,
        EXTENSION_POINT_REPORT_FACTORIES)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IReportFactory reportFactory = (IReportFactory) PluginUtilities.instantiate(parameter, pluginManager);
        model.getReportRegistry().addReports(reportFactory.createReport(resources, model.getExtensionPointRegistry()));
      }
    }
  }

  private void initializeMenus() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_MENUBAR)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IAnathemaMenu menu = (IAnathemaMenu) PluginUtilities.instantiate(parameter, pluginManager);
        menu.add(resources, model, view.getMenuBar());
      }
    }
  }

  private void initializeTools() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_TOOLBAR)) {
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IAnathemaTool tool = (IAnathemaTool) PluginUtilities.instantiate(parameter, pluginManager);
        tool.add(resources, model, view.getToolbar());
      }
    }
  }
}