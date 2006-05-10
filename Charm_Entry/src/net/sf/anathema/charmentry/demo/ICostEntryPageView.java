package net.sf.anathema.charmentry.demo;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.view.ICostEntryView;

public interface ICostEntryPageView extends IPageContent {

  public ICostEntryView addCostEntryView(String typeLabel, String costLabel, String textLabel);

}
