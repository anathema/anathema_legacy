package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class SetOtherTraits extends AbstractSetTraitFixture {

  @Override
  protected ITraitType getTraitType() {
    return OtherTraitType.valueOf(traitType);
  }
}