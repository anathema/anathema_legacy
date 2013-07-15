package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

public interface IBootJob {

  void run(Resources resources, IApplicationModel model);
}