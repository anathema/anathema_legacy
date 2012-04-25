package net.sf.anathema.character;

import java.awt.Image;

import javax.swing.Icon;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.lib.resources.IResources;

public class NullResources implements IResources {

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return key;
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(key);
    stringBuilder.append(","); //$NON-NLS-1$
    for (Object arg : arguments) {
      stringBuilder.append(String.valueOf(arg));
      stringBuilder.append(","); //$NON-NLS-1$
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean supportsKey(String key) {
    return true;
  }

  @Override
  public Image getImage(Class< ? > requestor, String relativePath) {
    return null;
  }

  @Override
  public Icon getImageIcon(Class< ? > requestor, String relativePath) {
    return null;
  }
  
  public <T extends IExtensibleDataSet> T getDataSet(Class<T> set) {
	return null;
  }
}