package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import fit.RowFixture;

public class AttributeListFixture extends RowFixture {

  @Override
  public Object[] query() throws Exception {
    ITraitType[] traitTypes = AttributeType.values();
    AcceptanceTrait[] traits = new AcceptanceTrait[traitTypes.length];
    @SuppressWarnings("unchecked")
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    ICoreTraitConfiguration traitConfiguration = statistics.getTraitConfiguration();
    for (int index = 0; index < traits.length; index++) {
      IDefaultTrait trait = (IDefaultTrait) traitConfiguration.getTrait(traitTypes[index]);
      traits[index] = new AcceptanceTrait(trait);
    }
    return traits;
  }

  @Override
  public Class< ? > getTargetClass() {
    return AcceptanceTrait.class;
  }
}