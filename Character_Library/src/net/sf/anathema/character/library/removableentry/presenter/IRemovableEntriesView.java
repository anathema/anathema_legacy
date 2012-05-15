package net.sf.anathema.character.library.removableentry.presenter;

import net.sf.anathema.character.library.trait.IModifiableCapTrait;

import javax.swing.Icon;

public interface IRemovableEntriesView<V extends IRemovableEntryView> {

  V addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}