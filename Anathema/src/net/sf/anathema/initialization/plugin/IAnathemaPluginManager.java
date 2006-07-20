package net.sf.anathema.initialization.plugin;

import java.util.Collection;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.PluginDescriptor;

public interface IAnathemaPluginManager {

  public Collection<Extension> getExtension(String plugin, String extensionPoint);

  public Class getClass(String className, PluginDescriptor declaringPluginDescriptor) throws ClassNotFoundException;
}