package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;

public class CharacterPoints implements ICharacterPoints {

  private final ICharacterStatistics statistics;
  private final IExperiencePointManagement experiencePointManagement;

  public CharacterPoints(ICharacterStatistics statistics, IExperiencePointManagement experiencePointManagement) {
    this.statistics = statistics;
    this.experiencePointManagement = experiencePointManagement;
  }

  public int getExperiencePointsSpent() {
    return experiencePointManagement.getTotalCosts();
  }

  public int getExperiencePointsTotal() {
    return statistics.getExperiencePoints().getTotalExperiencePoints();
  }
}