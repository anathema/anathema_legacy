package net.sf.anathema.framework.module;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

@Extension(id="net.sf.anathema.framework.module.PreferencesElementsExtensionPoint")
public class PreferencesElementsExtensionPoint implements IAnathemaExtension {

  public static final String ID = PreferencesElementsExtensionPoint.class.getName();
  private final List<IPreferencesElement> elements = new ArrayList<IPreferencesElement>();

  @Override
  public void initialize(IResources resources, IDataFileProvider dataFileProvider, AnnotationFinder finder,
                         ResourceLoader loader) {
    // nothing to do
  }

  public IPreferencesElement[] getAllPreferencesElements() {
    return elements.toArray(new IPreferencesElement[elements.size()]);
  }

  public void addPreferencesElement(IPreferencesElement element) {
    elements.add(element);
  }
}