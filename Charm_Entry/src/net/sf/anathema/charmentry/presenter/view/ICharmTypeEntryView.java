package net.sf.anathema.charmentry.presenter.view;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface ICharmTypeEntryView extends IPageContent {

  public IObjectSelectionView addComboBoxRow(String label, ListCellRenderer renderer, Object[] objects);

  public JToggleButton addCheckBoxRow(String label);
}