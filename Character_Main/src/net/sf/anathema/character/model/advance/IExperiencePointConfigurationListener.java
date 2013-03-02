package net.sf.anathema.character.model.advance;

public interface IExperiencePointConfigurationListener {

  void entryAdded(IExperiencePointEntry entry);

  void entryRemoved(IExperiencePointEntry entry);

  void entryChanged(IExperiencePointEntry entry);
}