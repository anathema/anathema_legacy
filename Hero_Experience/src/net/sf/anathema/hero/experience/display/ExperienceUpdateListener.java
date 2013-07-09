package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.IExperiencePointEntry;

public interface ExperienceUpdateListener {
  void update(IExperiencePointEntry entry, int points, String description);
}