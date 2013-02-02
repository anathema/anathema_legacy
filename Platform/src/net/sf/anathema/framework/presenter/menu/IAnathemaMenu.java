package net.sf.anathema.framework.presenter.menu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaMenu {

  void add(IResources resources, IAnathemaModel model, MenuBar menubar);
}