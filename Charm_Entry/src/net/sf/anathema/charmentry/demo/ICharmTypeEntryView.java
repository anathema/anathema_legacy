package net.sf.anathema.charmentry.demo;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.demo.view.IReflexiveCharmSpecialsView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface ICharmTypeEntryView extends IPageContent {

  public IObjectSelectionView addComboBoxRow(String label, ListCellRenderer renderer, Object[] objects);

  public JToggleButton addCheckBoxRow(String label);

  public ISimpleCharmSpecialsView addSimpleCharmSpecialsView(
      String modifiersLabel,
      String speedLabel,
      String defenseLabel);

  public IReflexiveCharmSpecialsView addReflexiveCharmSpecialsView(
      String stepLabel,
      String defaultLabel,
      String defensiveLabel,
      String splitLabel);

}
