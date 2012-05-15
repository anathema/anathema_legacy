package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.lib.gui.dialog.core.IPageContent;

public interface ICostEntryPageView extends IPageContent {

  public ICostEntryView addCostEntryView(String typeLabel, String costLabel, String textLabel);

}
