package net.sf.anathema.character.model.advance;

public interface IExperiencePointConfiguration {

  IExperiencePointEntry[] getAllEntries();

  IExperiencePointEntry addEntry();

  void removeEntry(IExperiencePointEntry entry);

  int getTotalExperiencePoints();

  void addExperiencePointConfigurationListener(ExperiencePointConfigurationListener listener);

  int getExtraSpendings();
}