package net.sf.anathema.character.main.advance;

public interface ExperiencePointConfigurationListener {

  void entryAdded();

  void entryRemoved();

  void entryChanged();

  void selectionChanged(IExperiencePointEntry entry);
}