package net.sf.anathema.framework.module;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.registry.Registry;

public class PreferencesElementsExtensionPoint extends Registry<String, IPreferencesElement> implements IExtensionPoint {

  public static final String ID = PreferencesElementsExtensionPoint.class.getName();

  public IPreferencesElement[] getAllPreferencesElements() {
    List<IPreferencesElement> elements = new ArrayList<IPreferencesElement>();
    for (String id : getKeys()) {
      elements.add(get(id));
    }
    return elements.toArray(new IPreferencesElement[elements.size()]);
  }
}