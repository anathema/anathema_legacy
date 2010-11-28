package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.library.trait.aggregated.AggregatedSubTrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;

public class CheckAggregatedAbilitiesFixture extends CheckAbilitiesFixture {

  public String subTrait;

  public int subTraits() {
    return getTrait().getSubTraits().getSubTraits().length;
  }

  public int subTraitValue() {
    return getSubTrait().getCurrentValue();
  }

  private AggregatedSubTrait getSubTrait() {
    return (AggregatedSubTrait) getTrait().getSubTraits().getSubTrait(subTrait);
  }

  public int subTraitMinimum() {
    return getSubTrait().getMinimalValue();
  }

  public boolean isSubTraitCaste() {
    return getSubTrait().getFavorization().isCaste();
  }

  public boolean isSubTraitFavored() {
    return getSubTrait().getFavorization().isFavored();
  }

  @Override
  protected IAggregatedTrait getTrait() {
    return (IAggregatedTrait) super.getTrait();
  }
}