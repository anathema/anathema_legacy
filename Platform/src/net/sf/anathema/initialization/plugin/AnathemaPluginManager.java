package net.sf.anathema.initialization.plugin;

import java.util.Collection;

import net.sf.anathema.initialization.InitializationException;

import org.java.plugin.PluginLifecycleException;
import org.java.plugin.PluginManager;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;

public class AnathemaPluginManager implements IAnathemaPluginManager {

  private final PluginManager manager;

  public AnathemaPluginManager(PluginManager manager) {
    this.manager = manager;
  }

  @SuppressWarnings("unchecked")
  public Collection<Extension> getExtension(String plugin, String extensionPoint) {
    ExtensionPoint point = manager.getRegistry().getExtensionPoint(plugin, extensionPoint);
    return point.getConnectedExtensions();
  }

  @SuppressWarnings("unchecked")
  public void activatePlugins() throws InitializationException {
    Collection<PluginDescriptor> pluginDescriptors = manager.getRegistry().getPluginDescriptors();
    for (PluginDescriptor descriptor : pluginDescriptors) {
      try {
        manager.activatePlugin(descriptor.getId());
      }
      catch (PluginLifecycleException e) {
        throw new InitializationException("Failed to activate plugin " + descriptor.getId() + ".", e); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
  }

  public ClassLoader getClassLoader(Extension extension) {
    return manager.getPluginClassLoader(extension.getDeclaringPluginDescriptor());
  }
}