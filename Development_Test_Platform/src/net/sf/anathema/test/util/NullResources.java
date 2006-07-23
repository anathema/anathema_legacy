package net.sf.anathema.test.util;

import java.awt.Image;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;

public class NullResources implements IResources {

  public String getString(String key) {
    return key;
  }

  public String getString(String key, Object[] arguments) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(key);
    stringBuilder.append(","); //$NON-NLS-1$
    for (Object arg : arguments) {
      stringBuilder.append(String.valueOf(arg));
      stringBuilder.append(","); //$NON-NLS-1$
    }
    return stringBuilder.toString();
  }

  public boolean supportsKey(String key) {
    return true;
  }

  public Image getImage(Class< ? > requestor, String relativePath) {
    return null;
  }

  public Icon getImageIcon(Class< ? > requestor, String relativePath) {
    return null;
  }
}