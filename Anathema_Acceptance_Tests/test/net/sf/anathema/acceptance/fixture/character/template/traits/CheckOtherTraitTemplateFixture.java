package net.sf.anathema.acceptance.fixture.character.template.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class CheckOtherTraitTemplateFixture extends AbstractTraitTemplateFixture {

  @Override
  protected final ITraitType getTraitType() {
    return OtherTraitType.valueOf(traitType);
  }
}