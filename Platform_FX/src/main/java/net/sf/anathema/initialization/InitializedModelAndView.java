package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.ApplicationView;

public class InitializedModelAndView {
  public final ApplicationView view;
  public final IApplicationModel model;

  public InitializedModelAndView(ApplicationView view, IApplicationModel model) {
    this.view = view;
    this.model = model;
  }
}