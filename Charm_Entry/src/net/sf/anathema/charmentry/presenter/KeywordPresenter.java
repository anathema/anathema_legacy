package net.sf.anathema.charmentry.presenter;

import javax.swing.DefaultListCellRenderer;

import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class KeywordPresenter {

  private final CharmEntryModel model;
  private final IKeywordView view;
  private final IResources resources;

  public KeywordPresenter(CharmEntryModel model, IKeywordView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    view.addObjectSelectionView(
        new DefaultListCellRenderer(),
        resources.getString("CharmEntry.Keyword"), new BasicUi(resources).getMediumAddIcon()); //$NON-NLS-1$
  }
}