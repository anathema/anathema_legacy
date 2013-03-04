package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.IResources;

public interface IBootJob {

  void run(IResources resources, IApplicationModel model);
}