package net.sf.anathema.charmentry.presenter.view;

import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IHeaderDataEntryView extends IPageContent {

  public ITextView addLineTextRow(String label);

  public <V> IObjectSelectionView<V> addComboBoxRow(String label, ListCellRenderer renderer, V[] objects);

  public ISourceSelectionView addSourceView(
      String bookLabel,
      String pageLabel,
      IExaltedSourceBook[] predefinedSources,
      ListCellRenderer renderer);
}