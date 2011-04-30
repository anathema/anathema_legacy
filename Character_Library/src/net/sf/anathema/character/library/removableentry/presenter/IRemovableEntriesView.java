package net.sf.anathema.character.library.removableentry.presenter;

import javax.swing.Icon;

import net.sf.anathema.character.library.trait.IModifiableCapTrait;

public interface IRemovableEntriesView<V extends IRemovableEntryView> {

  public V addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string);

  public void removeEntryView(IRemovableEntryView removableView);
}