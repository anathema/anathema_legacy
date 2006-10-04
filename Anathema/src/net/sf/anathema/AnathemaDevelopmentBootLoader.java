package net.sf.anathema;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

import net.sf.anathema.initialization.InitializationException;

import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.DefaultPluginsCollector;
import org.java.plugin.util.ExtendedProperties;

public class AnathemaDevelopmentBootLoader {

  public static void main(String[] arguments) throws Exception {
    showSplashScreen();
    ObjectFactory factory = ObjectFactory.newInstance();
    PluginManager manager = factory.createManager(factory.createRegistry(), new AnathemaPathResolver());
    collectPlugins(manager);
    new Anathema(manager).startApplication();
  }

  private static void showSplashScreen() throws IOException, AWTException {
    int random = new Random().nextInt(5);
    BufferedInputStream inputStream = new BufferedInputStream(
        AnathemaBootLoader.class.getResourceAsStream("/icons/core/AnathemaSplash" + (random + 3) + ".png")); //$NON-NLS-1$ //$NON-NLS-2$
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int numChars;
    while ((numChars = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, numChars);
    }
    new TranslucentSplashScreen(Toolkit.getDefaultToolkit().createImage(outputStream.toByteArray())).showSplash(4000);
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
    builder.append(new File(".").toURL().getPath()); //$NON-NLS-1$
    builder.append(","); //$NON-NLS-1$
    builder.append(new File("./plugins").toURL().getPath()); //$NON-NLS-1$   
    Enumeration<URL> systemResources = ClassLoader.getSystemResources("."); //$NON-NLS-1$
    while (systemResources.hasMoreElements()) {
      builder.append(","); //$NON-NLS-1$
      builder.append(systemResources.nextElement().getPath());
    }
    return builder.toString();
  }
}