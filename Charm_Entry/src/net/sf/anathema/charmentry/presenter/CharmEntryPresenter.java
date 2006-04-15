package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.view.CharmEntryView;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryPresenter {

  private final CharmEntryModel model;
  private final CharmEntryView view;
  private final IResources resources;

  public CharmEntryPresenter(CharmEntryModel model, CharmEntryView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    new BasicDataPresenter(model, view.addBasicDataView(), resources).initPresentation();

  }
}
