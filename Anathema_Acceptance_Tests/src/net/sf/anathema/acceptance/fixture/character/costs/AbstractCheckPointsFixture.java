package net.sf.anathema.acceptance.fixture.character.costs;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;

public class AbstractCheckPointsFixture extends AbstractCharacterColumnFixture {

  protected IBonusPointManagement createManagement() {
    IBonusPointManagement management = new BonusPointManagement(getCharacterStatistics());
    management.recalculate();
    return management;
  }
}