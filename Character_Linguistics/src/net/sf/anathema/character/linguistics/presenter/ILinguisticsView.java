package net.sf.anathema.character.linguistics.presenter;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

public interface ILinguisticsView extends IRemovableEntriesView<IRemovableEntryView> {

  public IButtonControlledObjectSelectionView<Object> addSelectionView(
      String labelText,
      ITextFieldComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon);

}
