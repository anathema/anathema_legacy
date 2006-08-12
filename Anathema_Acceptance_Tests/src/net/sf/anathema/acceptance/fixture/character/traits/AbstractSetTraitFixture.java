package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import fitnesse.fixtures.RowEntryFixture;

public abstract class AbstractSetTraitFixture extends RowEntryFixture {

  public String traitType;
  public int value;

  @Override
  public void enterRow() throws Exception {
    IDefaultTrait ability = getTrait();
    ability.setCurrentValue(value);
  }

  protected final IDefaultTrait getTrait() {
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    ITrait trait = statistics.getTraitConfiguration().getTrait(getTraitType());
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

  protected abstract ITraitType getTraitType();
}