package net.sf.anathema.test.character.generic.framework.magic.stringbuilder;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.resources.IResources;

public class DummyResources implements IResources {
  private final Map<String, String> stringMap = new HashMap<String, String>();

  public boolean supportsKey(String key) {
    throw new NotYetImplementedException();
  }

  public void putString(String key, String value) {
    stringMap.put(key, value);
  }

  public String getString(String key) {
    return stringMap.get(key);
  }

  public String getString(String key, Object[] arguments) {
    throw new NotYetImplementedException();
  }

  public Image getImage(Class< ? > requestor, String relativePath) {
    throw new NotYetImplementedException();
  }

  public Icon getImageIcon(Class< ? > requestor, String relativePath) {
    throw new NotYetImplementedException();
  }

}