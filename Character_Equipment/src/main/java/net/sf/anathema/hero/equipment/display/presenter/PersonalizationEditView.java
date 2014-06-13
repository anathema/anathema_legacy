package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.lib.util.Closure;

public interface PersonalizationEditView {
  void setTitle(String title);

  void setDescription(String description);

  void whenTitleChanges(Closure<String> closure);

  void whenDescriptionChanges(Closure<String> closure);
}
