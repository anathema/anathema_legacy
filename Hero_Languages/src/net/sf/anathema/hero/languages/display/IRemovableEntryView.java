package net.sf.anathema.hero.languages.display;

import net.sf.anathema.interaction.Command;

public interface IRemovableEntryView {

  void addButtonListener(Command command);

  void delete();
}