package net.sf.anathema.character.library.selection;

import javax.swing.Icon;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;
import net.sf.anathema.character.library.trait.view.ITraitView;

public interface IRemovableStringEntriesView<V extends ITraitView< ? >> extends
    IRemovableEntriesView<IRemovableTraitView<V>> {

  public IStringSelectionView addSelectionView(String labelText, Icon addIcon);
}