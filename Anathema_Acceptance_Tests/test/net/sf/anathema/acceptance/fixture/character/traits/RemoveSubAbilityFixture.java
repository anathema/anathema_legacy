package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;

public class RemoveSubAbilityFixture extends AbstractCharacterRowEntryFixture {
  public String abilityType;
  public String subTrait;

  @Override
  public void enterRow() throws Exception {
    AbilityType typeObject = AbilityType.valueOf(abilityType);
    IAggregatedTrait trait = (IAggregatedTrait) getCharacterStatistics().getTraitConfiguration().getTrait(typeObject);
    ISubTrait removedTrait = trait.getSubTraits().getSubTrait(subTrait);
    trait.getSubTraits().removeSubTrait(removedTrait);
  }
}
