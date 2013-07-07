package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.interaction.Command;

public interface RemovableEntryView {

  void addButtonListener(Command command);

  void delete();
}