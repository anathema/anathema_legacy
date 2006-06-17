package net.sf.anathema.charmentry.presenter.view;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface ICharmTypeEntryView extends IPageContent {

  public IObjectSelectionView<CharmType> addComboBoxRow(String label, ListCellRenderer renderer, CharmType[] objects);

  public JToggleButton addCheckBoxRow(String label);
}