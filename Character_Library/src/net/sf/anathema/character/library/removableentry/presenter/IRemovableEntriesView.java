package net.sf.anathema.character.library.removableentry.presenter;

import javax.swing.Icon;

public interface IRemovableEntriesView<V extends IRemovableEntryView> {

  public V addEntryView(Icon removeIcon, String string);

  public void removeEntryView(IRemovableEntryView removableView);
}