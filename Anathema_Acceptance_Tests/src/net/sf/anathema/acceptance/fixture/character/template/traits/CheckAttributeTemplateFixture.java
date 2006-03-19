package net.sf.anathema.acceptance.fixture.character.template.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class CheckAttributeTemplateFixture extends AbstractTraitTemplateFixture {

  @Override
  protected final ITraitType getTraitType() {
    return AttributeType.valueOf(traitType);
  }
}