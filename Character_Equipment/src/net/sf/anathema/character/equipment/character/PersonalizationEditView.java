package net.sf.anathema.character.equipment.character;

import net.sf.anathema.lib.util.Closure;

public interface PersonalizationEditView {
  void show();

  void whenChangeIsConfirmed(Runnable runnable);

  void setTitle(String title);

  void setDescription(String description);

  void whenTitleChanges(Closure<String> closure);

  void whenDescriptionChanges(Closure<String> closure);
}
