package net.sf.anathema.charmentry.demo;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface IDurationEntryView extends IPageContent {

  public IObjectSelectionView addObjectSelectionView(String durationLabel, ListCellRenderer renderer, Object[] durations);
}