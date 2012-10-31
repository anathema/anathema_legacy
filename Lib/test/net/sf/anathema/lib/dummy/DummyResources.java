package net.sf.anathema.lib.dummy;

import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class DummyResources implements IResources {
  public static final Icon ANY_ICON = new EmptyIcon();
  private final Map<String, String> stringMap = new HashMap<>();

  @Override
  public boolean supportsKey(String key) {
    return stringMap.containsKey(key);
  }

  public void putString(String key, String value) {
    stringMap.put(key, value);
  }

  @Override
  public String getString(String key, Object... arguments) {
    if (arguments.length == 0) {
      return stringMap.get(key);
    }
    throw new NotYetImplementedException();
  }

  @Override
  public Image getImage(Class< ? > requestor, String relativePath) {
    throw new NotYetImplementedException();
  }
  
  @Override
  public Icon getImageIcon(Class< ? > requestor, String relativePath) {
    return ANY_ICON;
  }
}