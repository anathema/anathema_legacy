package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

public interface ICharmTypeEntryView extends IPageContent {

  IObjectSelectionView<CharmType> addComboBoxRow(String label, ListCellRenderer renderer, CharmType[] objects);

  JToggleButton addCheckBoxRow(String label);
}