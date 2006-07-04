package net.sf.anathema.initialization.plugin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import net.sf.anathema.framework.InitializationException;

import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.DefaultPluginsCollector;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.util.ExtendedProperties;

public class AnathemaPluginManager implements IAnathemaPluginManager {

  private final PluginManager manager = ObjectFactory.newInstance().createManager();

  @SuppressWarnings("unchecked")
  public void collectPlugins() throws InitializationException {
    try {
      DefaultPluginsCollector collector = new DefaultPluginsCollector();
      String pluginRepositories = getPluginRepositories();
      System.out.println(pluginRepositories);
      Properties properties = new Properties();
      properties.put("org.java.plugin.boot.pluginsRepositories", pluginRepositories); //$NON-NLS-1$
      collector.configure(new ExtendedProperties(properties));
      Collection<PluginLocation> collection = collector.collectPluginLocations();
      PluginLocation[] locations = collection.toArray(new PluginLocation[collection.size()]);
      manager.publishPlugins(locations);
    }
    catch (Throwable throwable) {
      throw new InitializationException(throwable);
    }
  }

  @SuppressWarnings("unchecked")
  public Collection<Extension> getExtension(String plugin, String extensionPoint) {
    ExtensionPoint point = manager.getRegistry().getExtensionPoint(plugin, extensionPoint);
    return point.getConnectedExtensions();
  }

  private String getPluginRepositories() throws IOException, MalformedURLException {
    StringBuilder builder = new StringBuilder();
    builder.append(new File(".").toURL().getPath()); //$NON-NLS-1$
    builder.append(","); //$NON-NLS-1$
    builder.append(new File("./plugins").toURL().getPath()); //$NON-NLS-1$
    builder.append(","); //$NON-NLS-1$
    Enumeration<URL> systemResources = ClassLoader.getSystemResources("."); //$NON-NLS-1$
    while (systemResources.hasMoreElements()) {
      builder.append(systemResources.nextElement().getPath());
    }
    return builder.toString();
  }
}