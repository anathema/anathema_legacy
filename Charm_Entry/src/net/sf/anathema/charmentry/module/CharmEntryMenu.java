package net.sf.anathema.charmentry.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryMenu implements IAnathemaMenu {

  public void add(IResources resources, IAnathemaModel model, IMenuBar menubar) {
    IMenu menu = menubar.addMenu(resources.getString("CharmEntry.Show.Name")); //$NON-NLS-1$
    menu.addMenuItem(ShowCharmEntryAction.createMenuAction(resources));
  }
}