package net.sf.anathema.charmentry.presenter.view;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

public interface IPrerequisitesEntryView extends IPageContent {

  public ISelectableTraitView addSelectablePrerequisiteView(String string, IIdentificate[] traits);

  public IIntValueView addEssencePrerequisiteView(String label, int minimum, int maximum);
}
