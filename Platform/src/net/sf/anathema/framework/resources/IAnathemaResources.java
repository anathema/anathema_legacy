package net.sf.anathema.framework.resources;

import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaResources extends IResources {

  public void addResourceBundle(String bundleName, ClassLoader classLoader);
}