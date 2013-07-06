package net.sf.anathema.hero.experience.display;

public interface ExperienceView {

  void initGui(IExperienceViewProperties properties);

  void addExperienceConfigurationViewListener(ExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);

  void addEntry(int experiencePoints, String text);

  void clearEntries();

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);

  int getNumberOfEntriesOnDisplay();
}