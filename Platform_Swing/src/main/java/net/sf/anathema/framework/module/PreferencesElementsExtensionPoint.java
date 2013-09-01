package net.sf.anathema.framework.module;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

import java.util.ArrayList;
import java.util.List;

@Extension(id="net.sf.anathema.framework.module.PreferencesElementsExtensionPoint")
public class PreferencesElementsExtensionPoint implements IAnathemaExtension {

  public static final String ID = PreferencesElementsExtensionPoint.class.getName();
  private final List<IPreferencesElement> elements = new ArrayList<>();

  @Override
  public void initialize(DataFileProvider dataFileProvider, ObjectFactory factory, ResourceLoader loader) {
    // nothing to do
  }

  public IPreferencesElement[] getAllPreferencesElements() {
    return elements.toArray(new IPreferencesElement[elements.size()]);
  }

  public void addPreferencesElement(IPreferencesElement element) {
    elements.add(element);
  }
}