package net.sf.anathema.integrated;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IntegratedItemViewListening;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.ToolAutoCollector;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class IntegratedPerspectivePresenter {
  private final IAnathemaModel model;
  private final IntegratedPerspectiveView view;
  private final IResources resources;
  private final Instantiater instantiater;

  public IntegratedPerspectivePresenter(IAnathemaModel model, IntegratedPerspectiveView view, IResources resources, Instantiater instantiater) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.instantiater = instantiater;
  }

  public void initPresentation() throws InitializationException {
    new IntegratedItemViewListening().init(resources, model, view);
    initializeTools();
  }

  private void initializeTools() throws InitializationException {
    Collection<IAnathemaTool> tools = instantiater.instantiateOrdered(ToolAutoCollector.class);
    for (IAnathemaTool tool : tools) {
      tool.add(resources, model, view.getToolBar());
    }
  }
}
