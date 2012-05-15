package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.ListCellRenderer;

public interface IHeaderDataEntryView extends IPageContent {

  ITextView addLineTextRow(String label);

  <V> IObjectSelectionView<V> addComboBoxRow(String label, ListCellRenderer renderer, V[] objects);

  ISourceSelectionView addSourceView(String bookLabel, String pageLabel, IExaltedSourceBook[] predefinedSources, ListCellRenderer renderer);
}