package net.sf.anathema.charmentry.demo;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IHeaderDataEntryView extends IPageContent {

  public ITextView addLineTextRow(String label);

  public IObjectSelectionView addComboBoxRow(String label, ListCellRenderer renderer, Object[] objects);

  public ISourceSelectionView addSourceView(String bookLabel, String pageLabel, Object[] predefinedSources);
}