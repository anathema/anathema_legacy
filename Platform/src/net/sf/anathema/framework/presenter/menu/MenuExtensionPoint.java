package net.sf.anathema.framework.presenter.menu;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

@Extension(id = "Menu.Extra")
public class MenuExtensionPoint implements IAnathemaExtension, IMenuExtensionPoint {

  private final List<IMenuItem> menuItems = new ArrayList<>();

  @Override
  public void initialize(IResources resources, IDataFileProvider dataFileProvider, AnnotationFinder finder,
                         ResourceLoader loader) {
    // nothing to do
  }

  @Override
  public IMenuItem[] getMenuItems() {
    return menuItems.toArray(new IMenuItem[menuItems.size()]);
  }

  @Override
  public void addMenuItem(IMenuItem item) {
    menuItems.add(item);
  }
}