package net.sf.anathema.initialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.logging.Logger;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class ModuleCollection implements IModuleCollection {

  private static final String EXTENSION_POINT_MAIN_MODULES = "MainModules"; //$NON-NLS-1$
  private Logger logger = Logger.getLogger(ModuleCollection.class);
  private IAnathemaModule[] allModules;
  private final IAnathemaPluginManager pluginManager;

  public ModuleCollection(IAnathemaPluginManager pluginManager) {
    this.pluginManager = pluginManager;
    allModules = collectModules();
  }

  public void forAllDo(IClosure<IAnathemaModule> closure) {
    for (IAnathemaModule module : allModules) {
      closure.execute(module);
    }
  }

  private IAnathemaModule[] collectModules() {
    List<String> moduleClassNames = new ArrayList<String>();
    Collection<Extension> connectedExtensions = pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_MAIN_MODULES);
    for (Extension extension : connectedExtensions) {
      Parameter parameter = extension.getParameter("class"); //$NON-NLS-1$
      moduleClassNames.add(parameter.valueAsString());
    }
    List<IAnathemaModule> installedModules = new ArrayList<IAnathemaModule>();
    for (String className : moduleClassNames) {
      IAnathemaModule module = instantiateModule(className);
      if (module != null) {
        installedModules.add(module);
      }
    }
    return installedModules.toArray(new IAnathemaModule[0]);
  }

  private IAnathemaModule instantiateModule(String className) {
    try {
      Class< ? > moduleClass = Class.forName(className);
      return (IAnathemaModule) moduleClass.newInstance();
    }
    catch (ClassNotFoundException e) {
      logger.info("Module not installed: " + className); //$NON-NLS-1$
    }
    catch (Exception e) {
      logger.error("Error initializing module " + className, e); //$NON-NLS-1$
    }
    return null;
  }
}