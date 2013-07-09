package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.IExperiencePointEntry;

public interface ExperienceView {

  void initGui(IExperienceViewProperties properties);

  void addExperienceConfigurationViewListener(ExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);

  void addEntry(IExperiencePointEntry entry);

  void clearEntries();

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);

  int getNumberOfEntriesOnDisplay();

  void setSelection(IExperiencePointEntry entry);
}