package net.sf.anathema.charmentry.demo;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.presenter.ISelectableTraitView;
import net.sf.anathema.framework.value.IIntValueView;

public interface IPrerequisitesEntryView extends IPageContent {

  public ISelectableTraitView addSelectablePrerequisiteView(String string, Object[] traits);

  public IIntValueView addEssencePrerequisiteView(String label, int minimum, int maximum);
}
