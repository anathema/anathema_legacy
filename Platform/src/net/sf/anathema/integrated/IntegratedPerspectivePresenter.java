package net.sf.anathema.integrated;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IntegratedItemViewListening;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

public class IntegratedPerspectivePresenter {
  private final IAnathemaModel model;
  private final IntegratedPerspectiveView view;
  private final IResources resources;

  public IntegratedPerspectivePresenter(IAnathemaModel model, IntegratedPerspectiveView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() throws InitializationException {
    new IntegratedItemViewListening().init(resources, model, view);
    new IntegratedPerspectiveTool().add(resources, model, view.getToolBar());
  }
}