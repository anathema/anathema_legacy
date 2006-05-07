package net.sf.anathema.charmentry.demo;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.presenter.ISelectableTraitView;
import net.sf.anathema.framework.value.IIntValueView;

public interface IPrerequisitesEntryView extends IPageContent {

  public ISelectableTraitView addSelectablePrerequisiteView(Object[] traits);

  public IIntValueView addPrerequisiteView(String label, String traitLabel, int minimum, int maximum);

}
