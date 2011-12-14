package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class CheckOtherTraitsFixture extends AbstractCheckTraitFixture {

  @Override
  protected ITraitType getTraitType() {
    return OtherTraitType.valueOf(traitType);
  }
}