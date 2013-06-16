package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.ListCellRenderer;

public interface ILinguisticsView {

  IButtonControlledObjectSelectionView<Object> addSelectionView(String labelText, ITextFieldComboBoxEditor editor, ListCellRenderer renderer,
                                                                RelativePath addIcon);

  IOverviewCategory addOverview(String border);

  IRemovableEntryView addEntryView(RelativePath removeIcon, Trait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}