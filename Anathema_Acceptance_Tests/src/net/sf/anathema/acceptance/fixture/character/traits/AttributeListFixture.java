package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import fit.RowFixture;

public class AttributeListFixture extends RowFixture {

  @Override
  public Object[] query() throws Exception {
    ITraitType[] traitTypes = AttributeType.values();
    AcceptanceTrait[] traits = new AcceptanceTrait[traitTypes.length];
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    for (int index = 0; index < traits.length; index++) {
      traits[index] = new AcceptanceTrait(statistics.getTraitConfiguration().getTrait(traitTypes[index]));
    }
    return traits;
  }
  
  @Override
  public Class<?> getTargetClass() {
    return AcceptanceTrait.class;
  }
}