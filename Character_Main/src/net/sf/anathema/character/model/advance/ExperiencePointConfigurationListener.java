package net.sf.anathema.character.model.advance;

public interface ExperiencePointConfigurationListener {

  void entryAdded(IExperiencePointEntry entry);

  void entryRemoved(IExperiencePointEntry entry);

  void entryChanged(IExperiencePointEntry entry);
}