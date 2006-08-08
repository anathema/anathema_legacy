package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public interface IAnathemaBootJob {

  public void run(IResources resources, IAnathemaModel model, IAnathemaView view);
}