package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
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

  private IDefaultTrait getDefaultTrait() {
    ITrait trait = getTrait();
    final IDefaultTrait[] defaultTrait = new IDefaultTrait[1];
    trait.accept(new ITraitVisitor() {
    
      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        defaultTrait[0] = visitedTrait;
      }
    
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        defaultTrait[0] = visitedTrait.getSubTraits().getSubTraits()[0];
      }
    });
    return defaultTrait[0];
  }

  protected final ITrait getTrait() {
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    return statistics.getTraitConfiguration().getTrait(getTraitType());
  }

  protected abstract ITraitType getTraitType();
}