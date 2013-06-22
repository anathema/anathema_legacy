package net.sf.anathema.character.view.advance;

import net.sf.anathema.character.presenter.advance.ExperienceUpdateListener;

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