package net.sf.anathema.charmentry.presenter.view;

import javax.swing.JToggleButton;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.framework.value.IIntValueDisplay;

public interface IReflexiveSpecialsView extends IPageContent {

  public JToggleButton addCheckBoxRow(String label);

  public IIntValueDisplay addIntegerSelectionView(String typeLabel, int minimum, int maximum);
}