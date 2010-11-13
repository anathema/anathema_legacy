package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class CheckVirtuesFixture extends AbstractCheckTraitFixture {

  @Override
  protected ITraitType getTraitType() {
    return VirtueType.valueOf(traitType);
  }
}