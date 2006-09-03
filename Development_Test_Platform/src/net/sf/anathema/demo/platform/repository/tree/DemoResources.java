package net.sf.anathema.demo.platform.repository.tree;

import java.awt.Image;

import javax.swing.Icon;

import net.sf.anathema.framework.resources.ImageProvider;
import net.sf.anathema.lib.resources.IAnathemaImageProvider;
import net.sf.anathema.lib.resources.IResources;

public class DemoResources implements IResources {

  private final IAnathemaImageProvider imageProvider = new ImageProvider("icons"); //$NON-NLS-1$

  public boolean supportsKey(String key) {
    return true;
  }

  public String getString(String key, Object... arguments) {
    return key.substring(1);
  }

  public Image getImage(Class< ? > requestor, String relativePath) {
    return imageProvider.getImage(requestor, relativePath);
  }

  public Icon getImageIcon(Class< ? > requestor, String relativePath) {
    return imageProvider.getImageIcon(requestor, relativePath);
  }
}