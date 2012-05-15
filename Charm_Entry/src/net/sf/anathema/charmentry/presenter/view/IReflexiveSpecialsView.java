package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.JToggleButton;

public interface IReflexiveSpecialsView extends IPageContent {

  public JToggleButton addCheckBoxRow(String label);

  public IIntValueDisplay addIntegerSelectionView(String typeLabel, int minimum, int maximum);
}