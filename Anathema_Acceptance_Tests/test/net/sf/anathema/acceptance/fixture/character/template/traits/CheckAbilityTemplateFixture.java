package net.sf.anathema.acceptance.fixture.character.template.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class CheckAbilityTemplateFixture extends AbstractTraitTemplateFixture {

  @Override
  protected final ITraitType getTraitType() {
    return AbilityType.valueOf(traitType);
  }
}