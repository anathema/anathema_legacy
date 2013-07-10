package net.sf.anathema.character.main.advance;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ExperiencePointEntry {

  int getExperiencePoints();

  void setExperiencePoints(int points);

  ITextualDescription getTextualDescription();
}