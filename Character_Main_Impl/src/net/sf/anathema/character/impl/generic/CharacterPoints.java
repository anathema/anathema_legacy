package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;

public class CharacterPoints implements ICharacterPoints {

  private final ICharacter character;
  private final IExperiencePointManagement experiencePointManagement;

  public CharacterPoints(ICharacter character, IExperiencePointManagement experiencePointManagement) {
    this.character = character;
    this.experiencePointManagement = experiencePointManagement;
  }

  @Override
  public int getExperiencePointsSpent() {
    return experiencePointManagement.getTotalCosts();
  }

  @Override
  public int getExperiencePointsTotal() {
    return character.getExperiencePoints().getTotalExperiencePoints();
  }
}