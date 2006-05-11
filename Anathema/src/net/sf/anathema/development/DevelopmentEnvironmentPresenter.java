package net.sf.anathema.development;

import javax.swing.JMenu;

import net.sf.anathema.charmentry.module.ShowCharmEntryAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.resources.StringProvider;
import net.sf.anathema.framework.view.IAnathemaView;

public class DevelopmentEnvironmentPresenter {

  private final IAnathemaView view;
  private final IAnathemaModel model;
  private final IAnathemaResources resources;

  public DevelopmentEnvironmentPresenter(IAnathemaModel model, IAnathemaView view, IAnathemaResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  private void addMenu() {
    JMenu developmentMenu = new JMenu("Development"); //$NON-NLS-1$
    view.getMenuBar().addMenu(developmentMenu);
    developmentMenu.add(new RepositoryViewAction("Show repository tree", model, resources)); //$NON-NLS-1$
    initCharmEntry(developmentMenu);
  }

  private void initCharmEntry(JMenu developmentMenu) {
    resources.addStringResourceHandler(new StringProvider("language.CharmEntry", resources.getLocale())); //$NON-NLS-1$
    developmentMenu.add(new ShowCharmEntryAction("Charm Entry", resources)); //$NON-NLS-1$
  }

  public void initPresentation() {
    addMenu();
  }
}