package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaModule {

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView anathemaView);
}