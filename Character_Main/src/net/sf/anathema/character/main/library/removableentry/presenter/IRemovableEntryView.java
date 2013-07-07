package net.sf.anathema.character.main.library.removableentry.presenter;

import net.sf.anathema.interaction.Command;

public interface IRemovableEntryView {

  void addButtonListener(Command command);

  void delete();
}