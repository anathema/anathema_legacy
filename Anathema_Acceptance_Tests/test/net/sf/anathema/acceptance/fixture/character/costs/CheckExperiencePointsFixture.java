package net.sf.anathema.acceptance.fixture.character.costs;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;

public class CheckExperiencePointsFixture extends AbstractCharacterColumnFixture {

  public int getExperiencePointsSpent() {
    return new ExperiencePointManagement(getCharacterStatistics()).getTotalCosts();
  }

}