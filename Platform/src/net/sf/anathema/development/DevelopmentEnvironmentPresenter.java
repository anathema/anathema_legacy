package net.sf.anathema.development;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class DevelopmentEnvironmentPresenter implements IPresenter {

  private final IAnathemaView view;
  private final IAnathemaModel model;
  private final IResources resources;

  public DevelopmentEnvironmentPresenter(IAnathemaModel model, IAnathemaView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  private void addMenu() {
    IMenu menu = view.getMenuBar().addMenu("Development"); //$NON-NLS-1$
    menu.addMenuItem(new RepositoryViewAction("Show repository tree", model, resources)); //$NON-NLS-1$
  }

  public void initPresentation() {
    addMenu();
  }
}