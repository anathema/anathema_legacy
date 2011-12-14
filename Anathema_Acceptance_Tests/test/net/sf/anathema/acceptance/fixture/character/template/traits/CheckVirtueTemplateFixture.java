package net.sf.anathema.acceptance.fixture.character.template.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class CheckVirtueTemplateFixture extends AbstractTraitTemplateFixture {

  @Override
  protected final ITraitType getTraitType() {
    return VirtueType.valueOf(traitType);
  }
}