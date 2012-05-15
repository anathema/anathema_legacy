package net.sf.anathema.character.library.selection;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;
import net.sf.anathema.character.library.trait.view.ITraitView;

import javax.swing.Icon;

public interface IRemovableStringEntriesView<V extends ITraitView< ? >> extends
    IRemovableEntriesView<IRemovableTraitView<V>> {

  IStringSelectionView addSelectionView(String labelText, Icon addIcon);
}