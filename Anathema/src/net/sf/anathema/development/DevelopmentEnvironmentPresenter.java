package net.sf.anathema.development;

import javax.swing.JMenu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class DevelopmentEnvironmentPresenter {

  private final IAnathemaView view;
  private final IAnathemaModel model;
  private final IResources resources;

  public DevelopmentEnvironmentPresenter(IAnathemaModel model, IAnathemaView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  private void addMenu() {
    JMenu developmentMenu = new JMenu("Development"); //$NON-NLS-1$
    view.getMenuBar().addMenu(developmentMenu);
    developmentMenu.add(new RepositoryViewAction("Show repository tree", model, resources)); //$NON-NLS-1$
  }

  public void initPresentation() {
    addMenu();
  }
}