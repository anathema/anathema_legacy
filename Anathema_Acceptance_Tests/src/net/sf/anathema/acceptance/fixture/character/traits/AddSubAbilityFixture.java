package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;

public class AddSubAbilityFixture extends AbstractCharacterRowEntryFixture {
  public String abilityType;
  public String subTrait;

  @Override
  public void enterRow() throws Exception {
    AbilityType typeObject = AbilityType.valueOf(abilityType);
    IAggregatedTrait trait = (IAggregatedTrait) getCharacterStatistics().getTraitConfiguration().getTrait(typeObject);
    trait.getSubTraits().addSubTrait(subTrait);
  }
}
