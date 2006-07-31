package net.sf.anathema.initialization.plugin;

import org.java.plugin.registry.Extension;

public interface IClassLoaderProvider {

  public ClassLoader getClassLoader(Extension declaringExtension);
}