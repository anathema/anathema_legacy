package net.sf.anathema;

import net.sf.anathema.initialization.InitializationException;
import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.DefaultPluginsCollector;
import org.java.plugin.util.ExtendedProperties;
import sun.security.action.GetPropertyAction;

import java.security.AccessController;
import java.util.Collection;
import java.util.Properties;

public class AnathemaDevelopmentBootLoader {

  public static void main(String[] arguments) throws Exception {
    if (isSplashScreenSupported()) {
      new AnathemaPrebootSplashscreen().displayStatusMessage("Collecting Plugins..."); //$NON-NLS-1$
    }
    ObjectFactory factory = ObjectFactory.newInstance();
    PluginManager manager = factory.createManager(factory.createRegistry(), new AnathemaPathResolver());
    collectPlugins(manager);
    new Anathema(manager).startApplication();
  }

  public static boolean isSplashScreenSupported() {
    String osName = AccessController.doPrivileged(new GetPropertyAction("os.name"));
    return !osName.contains("Mac OS X"); //$NON-NLS-1$ 
  }

  private static void collectPlugins(PluginManager manager) throws InitializationException {
    try {
      DefaultPluginsCollector collector = new DefaultPluginsCollector();
      String pluginRepositories = new PluginRepositoryCollector().getPluginRepositories();
      Properties properties = new Properties();
      properties.put("org.java.plugin.boot.pluginsRepositories", pluginRepositories); //$NON-NLS-1$
      collector.configure(new ExtendedProperties(properties));
      Collection<PluginLocation> collection = collector.collectPluginLocations();
      PluginLocation[] locations = collection.toArray(new PluginLocation[collection.size()]);
      manager.publishPlugins(locations);
    } catch (Exception e) {
      throw new InitializationException("An error occured while Anathema was collecting plugins.", e); //$NON-NLS-1$
    }
  }
}