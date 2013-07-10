package net.sf.anathema.character.main.advance;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final ITextualDescription description = new SimpleTextualDescription("");
  private int experiencePoints = 0;

  @Override
  public int getExperiencePoints() {
    return experiencePoints;
  }

  @Override
  public void setExperiencePoints(int value) {
    if (experiencePoints == value) {
      return;
    }
    this.experiencePoints = value;
  }

  @Override
  public ITextualDescription getTextualDescription() {
    return description;
  }
}