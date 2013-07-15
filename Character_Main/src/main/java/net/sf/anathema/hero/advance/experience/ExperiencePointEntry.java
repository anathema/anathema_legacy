package net.sf.anathema.hero.advance.experience;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ExperiencePointEntry {

  int getExperiencePoints();

  void setExperiencePoints(int points);

  ITextualDescription getTextualDescription();
}