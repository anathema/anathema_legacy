package net.sf.anathema.charmentry.presenter;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;

public interface IKeywordView extends IRemovableEntriesView<IRemovableEntryView>, IPageContent {

  public IButtonControlledObjectSelectionView addObjectSelectionView(ListCellRenderer renderer, String label, Icon icon);
}