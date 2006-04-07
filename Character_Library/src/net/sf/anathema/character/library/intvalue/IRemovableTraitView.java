package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.view.ITraitView;

public interface IRemovableTraitView<K extends ITraitView> extends IRemovableEntryView, ITraitView<K> {

  // Nothing to do
}