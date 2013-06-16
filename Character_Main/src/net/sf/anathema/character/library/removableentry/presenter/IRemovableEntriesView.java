package net.sf.anathema.character.library.removableentry.presenter;

import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.lib.file.RelativePath;

public interface IRemovableEntriesView<V extends IRemovableEntryView> {

  V addEntryView(RelativePath removeIcon, IDefaultTrait trait, String string);

  void removeEntryView(IRemovableEntryView removableView);
}