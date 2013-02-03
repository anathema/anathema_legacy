package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.MainView;

public class InitializedModelAndView {
  public final MainView view;
  public final IAnathemaModel model;

  public InitializedModelAndView(MainView view, IAnathemaModel model) {
    this.view = view;
    this.model = model;
  }
}