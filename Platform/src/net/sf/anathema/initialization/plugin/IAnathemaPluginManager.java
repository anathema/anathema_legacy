package net.sf.anathema.initialization.plugin;

import java.util.Collection;

import org.java.plugin.registry.Extension;

public interface IAnathemaPluginManager extends IClassLoaderProvider {

  public Collection<Extension> getExtension(String plugin, String extensionPoint);
}