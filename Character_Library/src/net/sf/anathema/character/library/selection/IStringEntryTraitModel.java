package net.sf.anathema.character.library.selection;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;

public interface IStringEntryTraitModel<V> extends IRemovableEntryModel<V> {

  void setCurrentName(String newValue);
}