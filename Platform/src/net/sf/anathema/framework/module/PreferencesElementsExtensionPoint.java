package net.sf.anathema.framework.module;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

public class PreferencesElementsExtensionPoint extends Registry<String, IPreferencesElement> implements IAnathemaExtension {

  public static final String ID = PreferencesElementsExtensionPoint.class.getName();
  
  public void initialize(IResources resources) {
    // nothing to do
  }

  public IPreferencesElement[] getAllPreferencesElements() {
    List<IPreferencesElement> elements = new ArrayList<IPreferencesElement>();
    for (String id : getKeys()) {
      elements.add(get(id));
    }
    return elements.toArray(new IPreferencesElement[elements.size()]);
  }
}