package net.sf.anathema.initialization;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.logging.Logger;

import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.DefaultPluginsCollector;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Extension.Parameter;
import org.java.plugin.util.ExtendedProperties;

public class ModuleCollection implements IModuleCollection {

  private Logger logger = Logger.getLogger(ModuleCollection.class);
  private IAnathemaModule[] allModules;

  public ModuleCollection() throws InitializationException {
    allModules = collectModules();
  }

  public void forAllDo(IClosure<IAnathemaModule> closure) {
    for (IAnathemaModule module : allModules) {
      closure.execute(module);
    }
  }

  private IAnathemaModule[] collectModules() throws InitializationException {
    List<String> moduleClassNames = new ArrayList<String>();
    PluginManager manager = ObjectFactory.newInstance().createManager();
    try {
      DefaultPluginsCollector collector = new DefaultPluginsCollector();
      String pluginRepositories = getPluginRepositories();
      Properties properties = new Properties();
      properties.put("org.java.plugin.boot.pluginsRepositories", pluginRepositories); //$NON-NLS-1$
      collector.configure(new ExtendedProperties(properties));
      Collection<PluginLocation> collection = collector.collectPluginLocations();
      PluginLocation[] locations = collection.toArray(new PluginLocation[collection.size()]);
      manager.publishPlugins(locations);
      ExtensionPoint point = manager.getRegistry().getExtensionPoint("net.sf.anathema.main", "MainModules"); //$NON-NLS-1$ //$NON-NLS-2$
      Collection<Extension> connectedExtensions = point.getConnectedExtensions();
      for (Extension extension : connectedExtensions) {
        Parameter parameter = extension.getParameter("class"); //$NON-NLS-1$
        moduleClassNames.add(parameter.valueAsString());
      }
    }
    catch (Throwable e) {
      throw new InitializationException(e);
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

  private String getPluginRepositories() throws IOException, MalformedURLException {
    StringBuilder builder = new StringBuilder();
    builder.append(new File("./plugins").toURL().getPath()); //$NON-NLS-1$
    Enumeration<URL> systemResources = ClassLoader.getSystemResources("./plugin"); //$NON-NLS-1$
    while (systemResources.hasMoreElements()) {
      if (builder.length() > 0) {
        builder.append(","); //$NON-NLS-1$
      }
      builder.append(systemResources.nextElement().getPath());
    }
    String pluginRepositories = builder.toString();
    // TODO: Development-Ausgabe entfernen
    System.err.println(pluginRepositories);
    return pluginRepositories;
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