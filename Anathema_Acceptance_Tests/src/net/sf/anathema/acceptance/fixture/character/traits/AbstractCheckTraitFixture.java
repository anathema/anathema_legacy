package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;

public abstract class AbstractCheckTraitFixture extends AbstractCharacterColumnFixture {

  public String traitType;

  public final int value() {
    ITrait foundTrait = getDefaultTrait();
    return foundTrait.getCurrentValue();
  }

  public final int minimumValue() {
    IDefaultTrait foundTrait = getDefaultTrait();
    return foundTrait.getMinimalValue();
  }

  public final int maximumValue() {
    ITrait foundTrait = getDefaultTrait();
    return foundTrait.getMaximalValue();
  }

  protected final IDefaultTrait getDefaultTrait() {
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    return (IDefaultTrait) statistics.getTraitConfiguration().getTrait(getTraitType());
  }

  protected abstract ITraitType getTraitType();
}