package net.sf.anathema.character.main.advance;

public interface ExperiencePointConfigurationListener {

  void entryAdded(IExperiencePointEntry entry);

  void entryRemoved(IExperiencePointEntry entry);

  void entryChanged(IExperiencePointEntry entry);
}