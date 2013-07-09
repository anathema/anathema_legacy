package net.sf.anathema.character.main.advance;

public interface IExperiencePointConfiguration {

  IExperiencePointEntry[] getAllEntries();

  IExperiencePointEntry addEntry();

  void removeEntry();

  int getTotalExperiencePoints();

  void addExperiencePointConfigurationListener(ExperiencePointConfigurationListener listener);

  int getExtraSpendings();

  void selectForChange(IExperiencePointEntry entry);

  void updateCurrentSelection(String description, int points);
}