package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.character.generic.framework.intvalue.ISelectableIntValueView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.util.Identified;

public interface IPrerequisitesEntryView extends IPageContent {

  ISelectableIntValueView<Identified> addSelectablePrerequisiteView(String string, Identified[] traits, int initial, int max);

  IIntValueView addEssencePrerequisiteView(String label, int minimum, int maximum);
}
