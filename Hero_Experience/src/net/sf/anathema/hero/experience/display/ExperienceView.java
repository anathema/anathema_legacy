package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.advance.ExperienceSelectionListener;
import net.sf.anathema.character.main.advance.IExperiencePointEntry;
import net.sf.anathema.interaction.Tool;

public interface ExperienceView {

  void initGui(ExperienceViewProperties properties);

  Tool addTool();

  void addSelectionListener(ExperienceSelectionListener listener);

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);

  void setEntries(IExperiencePointEntry... allEntries);

  void setTotalValueLabel(int overallExperiencePoints);

  void setSelection(IExperiencePointEntry entry);
}