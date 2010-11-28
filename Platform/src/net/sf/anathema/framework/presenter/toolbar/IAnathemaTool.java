package net.sf.anathema.framework.presenter.toolbar;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaTool {

  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar);
}