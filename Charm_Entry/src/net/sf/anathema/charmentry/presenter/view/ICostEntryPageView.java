package net.sf.anathema.charmentry.presenter.view;

import net.disy.commons.swing.dialog.core.IPageContent;

public interface ICostEntryPageView extends IPageContent {

  public ICostEntryView addCostEntryView(String typeLabel, String costLabel, String textLabel);

}
