package net.sf.anathema;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import net.sf.anathema.initialization.InitializationException;

import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.DefaultPluginsCollector;
import org.java.plugin.util.ExtendedProperties;

public class AnathemaDevelopmentBootLoader {

  public static void main(String[] arguments) throws Exception {
    if (!System.getProperty("java.version").startsWith("1.5")) { //$NON-NLS-1$ //$NON-NLS-2$
      new AnathemaPrebootSplashscreen().displayStatusMessage("Collecting Plugins..."); //$NON-NLS-1$
    }
    ObjectFactory factory = ObjectFactory.newInstance();
    PluginManager manager = factory.createManager(factory.createRegistry(), new AnathemaPathResolver());
    collectPlugins(manager);
    new Anathema(manager).startApplication();
  }

  @SuppressWarnings("unchecked")
  private static void collectPlugins(PluginManager manager) throws InitializationException {
    try {
      DefaultPluginsCollector collector = new DefaultPluginsCollector();
      String pluginRepositories = getPluginRepositories();
      Properties properties = new Properties();
      properties.put("org.java.plugin.boot.pluginsRepositories", pluginRepositories); //$NON-NLS-1$
      collector.configure(new ExtendedProperties(properties));
      Collection<PluginLocation> collection = collector.collectPluginLocations();
      PluginLocation[] locations = collection.toArray(new PluginLocation[collection.size()]);
      manager.publishPlugins(locations);
    }
    catch (Exception e) {
      throw new InitializationException("An error occured while Anathema was collecting plugins.", e); //$NON-NLS-1$
    }
  }

  private static String getPluginRepositories() throws IOException, MalformedURLException {
    StringBuilder builder = new StringBuilder();
    builder.append(getFilePath(".")); //$NON-NLS-1$
    builder.append(","); //$NON-NLS-1$
    builder.append(getFilePath("./plugins")); //$NON-NLS-1$   
    Enumeration<URL> systemResources = ClassLoader.getSystemResources("."); //$NON-NLS-1$
    while (systemResources.hasMoreElements()) {
      builder.append(","); //$NON-NLS-1$
      builder.append(systemResources.nextElement().getPath());
    }
    return builder.toString();
  }

  private static String getFilePath(String string) throws MalformedURLException {
    return new File(string).toURI().toURL().getPath();
  }
}