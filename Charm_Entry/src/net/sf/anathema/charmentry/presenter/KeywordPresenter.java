package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.charmentry.model.CharmEntryModel;
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
    view.addObjectSelectionView(null, null, null, null);

  }

}
