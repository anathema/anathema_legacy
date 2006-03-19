package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;

public abstract class AbstractCheckTraitFixture extends AbstractCharacterColumnFixture {

  public String traitType;

  public final int value() {
    ITrait foundTrait = getTrait();
    return foundTrait.getCurrentValue();
  }

  public final int minimumValue() {
    ITrait foundTrait = getTrait();
    return foundTrait.getMinimalValue();
  }

  public final int maximumValue() {
    ITrait foundTrait = getTrait();
    return foundTrait.getMaximalValue();
  }

  protected final ITrait getTrait() {
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    return statistics.getTraitConfiguration().getTrait(getTraitType());
  }

  protected abstract ITraitType getTraitType();
}