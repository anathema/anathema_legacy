package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class CheckAttributesFixture extends AbstractCheckTraitFixture {

  @Override
  protected ITraitType getTraitType() {
    return AttributeType.valueOf(traitType);
  }
}