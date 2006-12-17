package net.sf.anathema.initialization;

import java.util.Collection;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.messaging.IAnathemaMessageContainer;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class AnathemaPresenter {

  private static final String PARAM_CLASS = "class"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_BOOTJOBS = "Bootjobs"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_TOOLBAR = "Toolbar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_MENUBAR = "Menubar"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_REPORT_FACTORIES = "ReportFactories"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_PREFERENCE_ELEMENTS = "PreferenceElements"; //$NON-NLS-1$
  private final IAnathemaModel model;
  private final IAnathemaView view;
  private final IResources resources;
  private final Collection<IItemTypeConfiguration> itemTypeConfigurations;
  private final IAnathemaPluginManager pluginManager;

  public AnathemaPresenter(
      IAnathemaPluginManager pluginManager,
      IAnathemaModel model,
      IAnathemaView view,
      IResources resources,
      Collection<IItemTypeConfiguration> itemTypeConfigurations) {
    this.pluginManager = pluginManager;
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.itemTypeConfigurations = itemTypeConfigurations;
  }

  public void initPresentation() throws InitializationException {
    for (IItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.fillPresentationExtensionPoints(model.getExtensionPointRegistry(), resources, model, view);
    }
    initializePreferences();
    for (IItemTypeConfiguration configuration : itemTypeConfigurations) {
      configuration.registerViewFactory(model, resources);
    }
    runBootJobs();
    initializeMenus();
    initializeTools();
    initializeReports();
    IAnathemaMessageContainer messageContainer = model.getMessageContainer();
    init(messageContainer);
  }

  private void init(final IAnathemaMessageContainer messageContainer) {
    messageContainer.addChangeListener(new IChangeListener() {
      public void changeOccured() {
        view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
      }
    });
    view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
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
      for (Parameter parameter : PluginUtilities.getParameters(extension, PARAM_CLASS)) {
        IPreferencesElement element = (IPreferencesElement) PluginUtilities.instantiate(parameter, pluginManager);
        extensionPoint.addPreferencesElement(element);
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