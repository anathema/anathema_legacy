package net.sf.anathema.hero.experience.model;

import net.sf.anathema.character.main.advance.ExperiencePointEntry;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class DefaultExperiencePointEntry implements ExperiencePointEntry {

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