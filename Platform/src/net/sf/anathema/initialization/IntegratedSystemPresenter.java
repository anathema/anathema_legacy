package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IntegratedItemViewListening;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class IntegratedSystemPresenter {
  private final IAnathemaModel model;
  private final MainView view;
  private final IResources resources;
  private final Instantiater instantiater;

  public IntegratedSystemPresenter(IAnathemaModel model, MainView view, IResources resources, Instantiater instantiater) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.instantiater = instantiater;
  }

  public void initPresentation() throws InitializationException {
    new IntegratedItemViewListening().init(resources, model, view.getIntegratedItemViewManagement());
    initializeTools();
  }

  private void initializeTools() throws InitializationException {
    Collection<IAnathemaTool> tools = instantiater.instantiateOrdered(Tool.class);
    for (IAnathemaTool tool : tools) {
      tool.add(resources, model, view.getIntegratedToolbar());
    }
  }
}
