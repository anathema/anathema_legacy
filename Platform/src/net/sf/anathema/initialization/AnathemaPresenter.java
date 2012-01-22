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
import net.sf.anathema.initialization.plugin.IClassLoaderProvider;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
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
  private AnathemaReflections reflections;
  private final IAnathemaModel model;
  private final IAnathemaView view;
  private final IResources resources;
  private final Collection<IItemTypeConfiguration> itemTypeConfigurations;
  private final IAnathemaPluginManager pluginManager;

  public AnathemaPresenter(
          IAnathemaPluginManager pluginManager,
          AnathemaReflections reflections, IAnathemaModel model,
          IAnathemaView view,
          IResources resources,
          Collection<IItemTypeConfiguration> itemTypeConfigurations) {
    this.pluginManager = pluginManager;
    this.reflections = reflections;
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
      public void changeOccurred() {
        view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
      }
    });
    view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
  }

  private void runBootJobs() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_BOOTJOBS)) {
      for (Parameter parameter : extension.getParameters(PARAM_CLASS)) {
        IAnathemaBootJob job = instantiate(parameter, pluginManager, IAnathemaBootJob.class);
        job.run(resources, model, view);
      }
    }
  }

  private void initializePreferences() throws InitializationException {
    PreferencesElementsExtensionPoint extensionPoint = (PreferencesElementsExtensionPoint) model.getExtensionPointRegistry()
            .get(PreferencesElementsExtensionPoint.ID);
    Collection<IPreferencesElement> elements = new ReflectionsInstantiater(reflections).instantiateAll(PreferenceElement.class);
    for (IPreferencesElement element : elements) {
      extensionPoint.addPreferencesElement(element);
    }
  }

  private void initializeReports() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(
            IPluginConstants.PLUGIN_CORE,
            EXTENSION_POINT_REPORT_FACTORIES)) {
      for (Parameter parameter : extension.getParameters(PARAM_CLASS)) {
        IReportFactory reportFactory = instantiate(parameter, pluginManager, IReportFactory.class);
        model.getReportRegistry().addReports(reportFactory.createReport(resources, model.getExtensionPointRegistry()));
      }
    }
  }

  private void initializeMenus() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_MENUBAR)) {
      for (Parameter parameter : extension.getParameters(PARAM_CLASS)) {
        IAnathemaMenu menu = instantiate(parameter, pluginManager, IAnathemaMenu.class);
        menu.add(resources, model, view.getMenuBar());
      }
    }
  }

  private void initializeTools() throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_TOOLBAR)) {
      for (Parameter parameter : extension.getParameters(PARAM_CLASS)) {
        IAnathemaTool tool = instantiate(parameter, pluginManager, IAnathemaTool.class);
        tool.add(resources, model, view.getToolbar());
      }
    }
  }

  private <T> T instantiate(Parameter classParameter, IClassLoaderProvider provider, Class<T> clazz)
          throws InitializationException {
    String className = classParameter.valueAsString();
    try {
      return clazz.cast(Class.forName(className, true, provider.getClassLoader(classParameter.getDeclaringExtension()))
              .newInstance());
    } catch (Throwable e) {
      throw new InitializationException("Failed to load required class " + className + ".", e); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }
}