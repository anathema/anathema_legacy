package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.ApplicationView;

public class InitializedModelAndView {
  public final ApplicationView view;
  public final IAnathemaModel model;

  public InitializedModelAndView(ApplicationView view, IAnathemaModel model) {
    this.view = view;
    this.model = model;
  }
}