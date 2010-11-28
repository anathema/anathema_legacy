package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class SetAbilitiesFixture extends AbstractSetTraitFixture {

  @Override
  protected ITraitType getTraitType() {
    return AbilityType.valueOf(traitType);
  }
}