package net.sf.anathema.charmentry.presenter;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryTabView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;

public interface IKeywordView extends IRemovableEntryTabView<IRemovableEntryView> {

  public IObjectSelectionView addObjectSelectionView(ListCellRenderer renderer, String label, Icon icon);

}
