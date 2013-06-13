package net.sf.anathema.character.library.removableentry.presenter;

import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.lib.file.RelativePath;

public interface IRemovableEntriesView<V extends IRemovableEntryView> {

  V addEntryView(RelativePath removeIcon, IModifiableCapTrait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}