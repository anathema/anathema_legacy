package net.sf.anathema.framework.presenter.menu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaMenu {

  public void add(IResources resources, IAnathemaModel model, IMenuBar menubar);
}